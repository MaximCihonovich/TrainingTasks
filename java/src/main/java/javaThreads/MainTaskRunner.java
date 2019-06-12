package javaThreads;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class MainTaskRunner {

    public static void main(String[] args) {

        Phaser priceIncrease = new Phaser ();
        ReentrantLock auctionBlocker = new ReentrantLock();
        ArrayList <Auction> lotsList = new ArrayList<>();
        ArrayList <Buyer> buyerList = new ArrayList<>();
        Thread paymentCheck;

        for (byte i=1; i<13; ++i) {
            lotsList.add(new Auction("Товар " + i));
        }

        for (byte i=1; i<6; ++i) {
            buyerList.add(new Buyer("Покупатель " + i));
        }

        for (byte j=0; j<lotsList.size(); ++j) {
            System.out.printf("%nНа аукцион выставляется лот %s, начальной стоимостью %d.",lotsList.get(j).getLotName(),lotsList.get(j).getBaseCost());
            ExecutorService executor = Executors.newCachedThreadPool();
            for (Buyer buyer : buyerList) {
                if (buyer.getAuctionBan() == 0 ) {
                    executor.execute(new Bid(buyer,lotsList.get(j),auctionBlocker,priceIncrease));
                } else { buyer.setAuctionBan((byte) (buyer.getAuctionBan()-1));}
            }
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                executor.shutdownNow();
            }
            catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
                executor.shutdown();
            }
            System.out.println("\nАукцион завершён.");
            if (lotsList.get(j).getWinner() != null) {
                System.out.printf("%s купил %s, за %d.",lotsList.get(j).getLotName(),lotsList.get(j).getWinner().getBuyerName(),lotsList.get(j).getNewCost());
                paymentCheck = new Thread (new Payment(lotsList.get(j)));
                paymentCheck.start();
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                    if (paymentCheck.isAlive()) {paymentCheck.interrupt();}
                    if (lotsList.get(j).getPaymentCheck()) {
                        lotsList.get(j).getWinner().setFunds(lotsList.get(j).getWinner().getFunds()-lotsList.get(j).getNewCost());
                        System.out.println("\nОплачено.");
                    } else {
                        lotsList.get(j).getWinner().setAuctionBan((byte) 2);
                        lotsList.get(j).setNewCost(lotsList.get(j).getBaseCost());
                        System.out.println("\nНе оплачено.");
                        System.out.println("Лот " + lotsList.get(j).getLotName() + " будет выставлен снова.");
                        System.out.println(lotsList.get(j).getWinner().getBuyerName() + " отстроняется от аукциона на 2 лота");
                        lotsList.get(j).setWinner(null);
                        --j;
                    }
                }
                catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                System.out.println("В аукционе никто не участвовал.\n" + lotsList.get(j).getLotName() + "Будет выставлен снова по сниженной цене.");
                lotsList.get(j).setBaseCost(lotsList.get(j).getBaseCost()-lotsList.get(j).getBaseCost()/15);
                --j;
            }
        }
    }
}