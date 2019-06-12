package javaFundamentals.optional;

import java.util.Random;
import java.util.Scanner;

public class OptionalTaskRunner {
    public static void main (String [] args) {

        System.out.println(" Задача 5");
        System.out.println("Назовите себя: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        System.out.println("Приветствую вас " + name);

        System.out.println("\n Задача 2");
        Random randomNumber = new Random();
        StringBuilder stringOfRandomNumbers = new StringBuilder();
        int numbersWithChangeLine, numbersWithoutChangeLine;
        System.out.println("Введите количество случайных чисел с переходом на новую строку: ");
        numbersWithChangeLine = input.nextInt();
        for (int i = 0; i < numbersWithChangeLine; i++) {
            stringOfRandomNumbers.append(randomNumber.nextInt());
            stringOfRandomNumbers.append("\n");
        }
        System.out.println("Введите количество случайных чисел без перехода на новую строку: ");
        numbersWithoutChangeLine = input.nextInt();
        for (int i = 0; i < numbersWithoutChangeLine; i++) {
            stringOfRandomNumbers.append(randomNumber.nextInt());
            stringOfRandomNumbers.append("  ");
        }
        System.out.println(stringOfRandomNumbers.toString());

        System.out.println("\n Задача 5");
        String monthOutToScreen = "";
        do {
            System.out.println("Введите номер любого месяца: ");
            int monthNumber = input.nextInt();
            if (monthNumber > 1 && monthNumber <= 12) {
                for (Months month : Months.values()) {
                    monthOutToScreen = month.ordinal() == monthNumber - 1 ? month.toString().toLowerCase() : monthOutToScreen;
                }
            } else {
                System.out.println("Введите число от 1 до 12");
            }
        }
        while (monthOutToScreen.equals(""));
        System.out.println("Месяц: " + monthOutToScreen);
        input.close();
    }
}
