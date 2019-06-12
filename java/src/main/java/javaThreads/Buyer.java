package javaThreads;


class Buyer {

    private int funds;
    private byte auctionBan = 0;
    private String buyerName;

    Buyer(String name) {
        funds = (int) (Math.random()*750)+100;
        this.buyerName = name;
    }

    String getBuyerName() { return buyerName; }

    byte getAuctionBan () { return auctionBan; }

    void setAuctionBan (byte banLenght) { auctionBan = banLenght; }

    int getFunds () { return funds; }

    void setFunds (int fundsBalance) { funds = fundsBalance; }

}
