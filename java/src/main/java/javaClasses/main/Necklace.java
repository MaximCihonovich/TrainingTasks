package javaClasses.main;

import javaClasses.main.stones.GemOfNecklace;
import javaClasses.main.stones.Gemstone;
import java.util.ArrayList;
import java.util.Comparator;

class Necklace {

    private ArrayList <GemOfNecklace> necklace = new ArrayList<>();

    ArrayList <GemOfNecklace> getNecklace () {
        return necklace;
    }

    void sortGemsOfNecklaceByCost() {
        necklace.sort(Comparator.comparing(GemOfNecklace::getCost));
    }

    ArrayList <GemOfNecklace> sortGemsOfNecklaceByTransparency(String transparency) {
        ArrayList <GemOfNecklace> sortedGemstoneList = new ArrayList<>();
        for (GemOfNecklace gemstone : necklace) {
            if (gemstone.getTransparency().equals(transparency)) {
                sortedGemstoneList.add(gemstone); }
        }
        return sortedGemstoneList;
    }

    void addGemstoneToNecklace (Gemstone gemstone, byte count) {
        necklace.add(new GemOfNecklace(gemstone.getName(),gemstone.getColor(),gemstone.getTransparency(),gemstone.getWeight(),gemstone.getCost(),count));
    }

    String calculateNecklaceCost() {
        float gemstonesCost, gemstonesWeight;
        float necklaceTotalCost = 0;
        float necklaceTotalWeight = 0;
        StringBuilder calculatedNecklace = new StringBuilder();
        calculatedNecklace.append(String.format("%n %-22s | %-14s | %-12s | %-11s | %-14s","Название","Цвет","Количество","Вес","Цена"));
        for (GemOfNecklace gemstone : necklace
        ) {
            gemstonesWeight = gemstone.getCount()*gemstone.getWeight();
            gemstonesCost = gemstone.getCount()*gemstone.getCost();
            necklaceTotalWeight += gemstonesWeight;
            necklaceTotalCost += gemstonesCost;
            calculatedNecklace.append(String.format("%n %-22s | %-14s | %12d | %5.2f карат | %,12.2f $",gemstone.getName(),gemstone.getColor(),gemstone.getCount(),gemstonesWeight,gemstonesCost));
        }
        calculatedNecklace.append(String.format("%n Вес ожерелья %49.2f карат %n Цена ожерелья %,69.2f $%n",necklaceTotalWeight,necklaceTotalCost));
        return calculatedNecklace.toString();
    }
}
