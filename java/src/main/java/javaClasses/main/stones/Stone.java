package javaClasses.main.stones;

public class Stone {

    private String name, color;

    Stone(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName () {
        return name;
    }

    public String getColor () {
        return color;
    }
}
