package javaClasses.main;

import javaClasses.main.stones.Gemstone;
import javaClasses.main.stones.GemOfNecklace;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainTaskRunner {
    public static void main(String[] args) {

        StringBuilder initializationString = new StringBuilder();
        String inputData;
        String [] dataStringsArray;
        Gemstone[] gemstones;
        try (BufferedReader fileReader = new BufferedReader(new FileReader("data/stone.lst"))) {
                while ((inputData=fileReader.readLine())!=null) {
                initializationString.append(inputData);
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        dataStringsArray = initializationString.toString().split(" ");
        gemstones = new Gemstone[dataStringsArray.length/5];
        for (byte i=0; i < gemstones.length; ++i) {
            gemstones [i] = new Gemstone (dataStringsArray[i*5],dataStringsArray[i*5+1],dataStringsArray[i*5+2],Float.parseFloat(dataStringsArray[i*5+3]),Float.parseFloat(dataStringsArray[i*5+4]));
        }

        Necklace necklace = new Necklace();
        byte menuSelector, gemstoneNumber, numberOfGemstones, transparencyType;
        boolean exitFlag = true;
        do {
            Scanner  inputStream = new Scanner(System.in);
            System.out.printf("%n Выберите действие: %n%s %n%s %n%s %n%s %n%s %n%s %n%s%n",
                    "1. Вывести список доступных камней",
                    "2. Добавить камень в ожерелье",
                    "3. Отсортировать по цене и вывести список камней ожерелья",
                    "4. Вывести камни ожерелья указанной прозрачности",
                    "5. Посчитать стоимость ожерелья",
                    "6. Очистить список камней ожерелья",
                    "7. Завершить работу");
            try {menuSelector = inputStream.nextByte();
                switch (menuSelector) {
                    case 1 :
                        System.out.printf("%n  %-24s | %-14s | %-11s | %-12s%n","Название","Цвет","Вес","Цвена карата");
                        for (byte i=0; i < gemstones.length; ++i) {
                            System.out.printf("%-3d %s%n",i+1,gemstones[i].toString());
                        }
                        break;
                    case 2 :
                        System.out.println("Введите номер добавляемого камня");
                        try {gemstoneNumber = inputStream.nextByte();
                            if (gemstoneNumber > 0 && gemstoneNumber <= gemstones.length) {
                                System.out.println("Введите количество добавляемых камней");
                                try {numberOfGemstones = inputStream.nextByte();
                                    necklace.addGemstoneToNecklace(gemstones[gemstoneNumber-1],numberOfGemstones);
                                }
                                catch (InputMismatchException ex) {
                                    System.out.println("Введите целое число");
                                }
                            }
                            else {System.out.println("Такого номера нет в списке");
                            }
                        }
                        catch (InputMismatchException ex) {
                            System.out.println("Введите целое число");
                        }
                        break;
                    case 3 :
                        if (!(necklace.getNecklace().isEmpty())) {
                            necklace.sortGemsOfNecklaceByCost();
                            System.out.printf("%n%-22s | %-14s | %-16s | %-12s%n","Название","Цвет","Прозрачность","Количество");
                            for (GemOfNecklace gemstone : necklace.getNecklace()) {
                                System.out.println(gemstone.toString());
                            }
                        } else {System.out.println("Добавьте камни в ожерелье");}
                        break;
                    case 4 :
                        if (!(necklace.getNecklace().isEmpty())) {
                            System.out.println("Выберите прозрачность камней");
                            System.out.printf("%n 1.%s %n 2.%s %n 3.%s%n","прозрачный","просвечивающий","непрозрачный");
                            try {transparencyType = inputStream.nextByte();
                                switch (transparencyType) {
                                    case 1 :
                                        System.out.printf("%n%-22s | %-14s | %-16s | %-12s%n","Название","Цвет","Прозрачность","Количество");
                                        for (GemOfNecklace gemstone : necklace.sortGemsOfNecklaceByTransparency("прозрачный")
                                        ) {
                                            System.out.println(gemstone.toString());
                                        }
                                        break;
                                    case  2 :
                                        System.out.printf("%n%-22s | %-14s | %-16s | %-12s%n","Название","Цвет","Прозрачность","Количество");
                                        for (GemOfNecklace gemstone : necklace.sortGemsOfNecklaceByTransparency("просвечивающий")
                                        ) {
                                            System.out.println(gemstone.toString());
                                        }
                                        break;
                                    case 3 :
                                        System.out.printf("%n%-22s | %-14s | %-16s | %-12s%n","Название","Цвет","Прозрачность","Количество");
                                        for (GemOfNecklace gemstone : necklace.sortGemsOfNecklaceByTransparency("непрозрачный")
                                        ) {
                                            System.out.println(gemstone.toString());
                                        }
                                        break;
                                    default : System.out.println("Введите число от 1 до 3");
                                }
                            }
                            catch (InputMismatchException ex) {
                                System.out.println("Вы ввели не число");
                            }
                        } else {System.out.println("Добавьте камни в ожерелье");}
                        break;
                    case 5 :
                        if (!(necklace.getNecklace().isEmpty())) {
                            System.out.println(necklace.calculateNecklaceCost());
                        } else {System.out.println("Добавьте камни в ожерелье");}
                        break;
                    case 6 :
                        necklace = new Necklace();
                        break;
                    case 7 :
                        exitFlag = false;
                        inputStream.close();
                        break;
                    default : System.out.println("Введите число от 1 до 7");
                    }
            }
            catch (InputMismatchException ex) {
                System.out.println("Введите число от 1 до 7");
            }
        } while (exitFlag);
    }
}
