package javaClasses.main.stones;

public class Gemstone extends Stone {

    private String transparency;
    private Float weight, cost;

    public Gemstone(String name, String color, String transparency, Float weight, Float cost) {
        super(name, color);
        this.transparency = transparency;
        this.weight = weight;
        this.cost = cost;
    }

    public String getTransparency () {
        return transparency;
    }

    public Float getWeight () { return weight; }

    public Float getCost () { return cost; }

    @Override
    public String toString () {
        return String.format("%-22s | %-14s | %-5.2f карат | %-,10.2f $",super.getName(),super.getColor(),weight,cost);
    }
}
