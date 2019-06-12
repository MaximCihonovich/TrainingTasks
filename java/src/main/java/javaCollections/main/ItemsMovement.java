package javaCollections.main;

import java.util.LinkedList;

public class ItemsMovement {

    private int listSize;
    private LinkedList<Byte> numbersList = new LinkedList<>();

    ItemsMovement(int listSize) {
        this.listSize = listSize;
        for (byte i=0; i < listSize; ++i) {
            numbersList.add((byte)((Math.random()*99)+1));
        }
    }

    void moveListItems(int indexOfAnchorElement) {
        byte anchorElement = numbersList.get(indexOfAnchorElement);
        for (byte i=0; i < listSize; ++i) {
            if (anchorElement > numbersList.get(i)) {
                numbersList.addFirst(numbersList.remove(i));
            }
        }
        for (byte i=(byte) (listSize-1); i >= 0; --i) {
            if (anchorElement < numbersList.get(i)) {
                numbersList.addLast(numbersList.remove(i));
            }
        }
    }

    byte getItemAnchor (int itemIndex) { return numbersList.get(itemIndex);}

    @Override
    public String toString () {
        StringBuilder listString = new StringBuilder();
        for (byte number : numbersList) {
            listString.append(number);
            listString.append(" ");
        }
        return listString.toString();
    }
}
