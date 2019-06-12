package javaClasses.main.stones;

public class GemOfNecklace extends Gemstone {

    private int count;

    public GemOfNecklace(String name, String color, String transparency, Float weight, Float cost, int count) {
        super(name, color, transparency, weight, cost);
        this.count = count;
    }

    public int getCount () {
        return count;
    }

    @Override
    public String toString () {
        return String.format("%-22s | %-14s | %-16s | %4d",super.getName(),super.getColor(),super.getTransparency(),count);
    }

}
