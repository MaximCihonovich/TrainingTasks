package javaThreads;

class Auction {

    private String lotName;
    private int baseCost;
    private int newCost;
    private Buyer winner;
    private boolean paymentCheck;

    Auction(String lotName) {
        this.lotName = lotName;
        baseCost = (int)(Math.random()*100)+10;
        newCost = baseCost;
    }

    String getLotName () { return lotName; }

    int getBaseCost () {
        return baseCost;
    }

    void setBaseCost (int cost) { baseCost = cost; }

    int getNewCost () {
        return newCost;
    }

    void setNewCost (int cost) {
        newCost = cost;
    }

    Buyer getWinner() { return winner; }

    void setWinner(Buyer buyer) { winner = buyer; }

    boolean getPaymentCheck() { return paymentCheck; }

    void setPaymentCheck (boolean check) { paymentCheck = check; }

}
