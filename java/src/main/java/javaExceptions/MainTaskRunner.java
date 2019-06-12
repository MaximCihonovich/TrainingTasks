package javaExceptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainTaskRunner {

    public static void main(String[] args) {

        StringBuilder stringOfData = new StringBuilder();
        String inputString;
        ArrayList <Double> numbersList;
        try ( BufferedReader fileInputStream = new BufferedReader(new FileReader("data/Numbers.txt"))){
            while ((inputString=fileInputStream.readLine())!=null) {
                stringOfData.append(inputString);
            }
            ArrayList <String> stringsList = new ArrayList<>(Arrays.asList(stringOfData.toString().split(" ")));
            try { numbersList = stringsListToFloatsList(stringsList);
                System.out.println("Массив чисел: " + numbersList);
                try {
                    System.out.println(calculateNumbers(numbersList));
                } catch (OutOfMaxValueException ex) {
                    System.out.println(ex.getMessage());
                    System.out.println(ex.getNumber());
                }
            } catch (NotNumberException ex) {
                System.out.println(ex.getMessage());
                System.out.println(ex.getConvertedString());
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static String calculateNumbers (ArrayList<Double> numbers) throws OutOfMaxValueException {

        double sumNumbers = 0;
        double averageOfNumbers;
        for (double number: numbers) {
            if (number > Float.MAX_VALUE) {
                throw new OutOfMaxValueException("Число за пределами максимально допустимого значения", number );
            }
            sumNumbers += number;
        }
        averageOfNumbers = sumNumbers / numbers.size();

        return String.format("Cумма чисел: %,f %nСреднее значение: %,f",sumNumbers,averageOfNumbers);
    }

    private static ArrayList<Double> stringsListToFloatsList (ArrayList<String> stringsList) throws NotNumberException {

        ArrayList<Double> numbersList = new ArrayList<>();
        for (String string : stringsList) {
            for (char character : string.replace(".","").toCharArray()) {
                if (!(Character.isDigit(character))) {
                    throw new NotNumberException("Это не число",string);
                }
            }
            numbersList.add(Double.parseDouble(string));
        }

        return numbersList;
    }
}
