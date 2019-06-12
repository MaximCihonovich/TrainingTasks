package javaThreads;

import java.util.concurrent.Phaser;
import java.util.concurrent.locks.ReentrantLock;

public class Bid implements Runnable {

    private Buyer buyer;
    private Auction auctionLot;
    private ReentrantLock auctionBlocker;
    private Phaser priceIncrease;

    Bid(Buyer buyer, Auction lot, ReentrantLock blocker, Phaser priceIncrease) {
        this.buyer = buyer;
        auctionLot = lot;
        auctionBlocker = blocker;
        this.priceIncrease = priceIncrease;
        this.priceIncrease.register();
    }

    @Override
    public void run() {

        auctionBlocker.lock();
        if ((buyer.getFunds()/4) > auctionLot.getNewCost()) {
                auctionLot.setNewCost(auctionLot.getNewCost()+ auctionLot.getNewCost()/20 +1);
                auctionLot.setWinner(buyer);
                }
        auctionBlocker.unlock();
        priceIncrease.arriveAndAwaitAdvance();
        auctionBlocker.lock();
        if ((buyer.getFunds()/4) > auctionLot.getNewCost()) {
                auctionLot.setNewCost(auctionLot.getNewCost()+ auctionLot.getNewCost()/25 +1);
                auctionLot.setWinner(buyer);
                }
        auctionBlocker.unlock();
        priceIncrease.arriveAndAwaitAdvance();

        auctionBlocker.lock();
        if ((buyer.getFunds()/2) > auctionLot.getNewCost()) {
                auctionLot.setNewCost(auctionLot.getNewCost()+ auctionLot.getBaseCost()/20 +1);
                auctionLot.setWinner(buyer);
                }
        auctionBlocker.unlock();
        priceIncrease.arriveAndDeregister();
    }
}
