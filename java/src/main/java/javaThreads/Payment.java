package javaThreads;

public class Payment implements Runnable {

    private Auction paymentCheck;

    Payment(Auction auction) {
        paymentCheck = auction;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((int) (Math.random()*1800)+100);
            paymentCheck.setPaymentCheck(true);
        }
        catch (InterruptedException ex) {
            paymentCheck.setPaymentCheck(false);
        }
    }
}
