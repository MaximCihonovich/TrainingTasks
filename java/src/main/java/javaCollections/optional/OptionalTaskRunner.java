package javaCollections.optional;

import java.io.*;
import java.util.*;

public class OptionalTaskRunner {

    public static void main(String[] args) {

        StringBuilder stringFromFile = new StringBuilder();
        String inputString;
        try (BufferedReader fileInputStream = new BufferedReader(new FileReader("data/StringsInput.txt"))){
            while ((inputString=fileInputStream.readLine())!=null) {
                stringFromFile.append(inputString);
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        ArrayDeque <String> stringsList = new ArrayDeque<>(Arrays.asList(stringFromFile.toString().split(" ")));
        try (BufferedWriter fileOutputStream = new BufferedWriter(new FileWriter("data/StringsOutput.txt",false))) {
            while (!(stringsList.isEmpty())) {
                fileOutputStream.write(stringsList.removeLast());
                fileOutputStream.write(" ");
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(" Задача 2");
        new File ("C:\\JavaLessons\\folder1").mkdirs();
        new File ("C:\\JavaLessons\\folder2").mkdir();
        new File ("C:\\JavaLessons\\folder3").mkdir();
        try {new File ("C:\\JavaLessons\\file1.txt").createNewFile();}
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        File items = new File("C:\\JavaLessons");
        ArrayList <String> directoryList = new ArrayList<>();
        for (File item : items.listFiles()) {
            if (item.isDirectory()) {
                directoryList.add(item.getName() + " - папка");
                new File(item.getAbsolutePath()).deleteOnExit();
            } else {
                directoryList.add(item.getName() + " - файл");
                new File(item.getAbsolutePath()).deleteOnExit();
            }
        }
        System.out.println(directoryList);
        new File ("C:\\JavaLessons").deleteOnExit();

        System.out.println("\n Задача 3");
        String verse = "Лепей не будзе. Будзе толькi горш. Ад панядзелку льецца дробны дождж.";
        ArrayList <String> listOfVerseStrings = new ArrayList<>(Arrays.asList(verse.split(" ")));
        for (byte i = 0; i < listOfVerseStrings.size(); ++i) {
            listOfVerseStrings.set(i, listOfVerseStrings.get(i).replace(".",""));
        }
        System.out.println("Оригинальный список строк " + listOfVerseStrings);
        listOfVerseStrings.sort(Comparator.comparing(String::length));
        System.out.println("Сортированный по длинне строк список " + listOfVerseStrings);

        System.out.println("\n Задача 4");
        int number = 0;
        char [] numberToList;
        ArrayDeque <Character> stack = new ArrayDeque <>();
        StringBuilder invertedNumber = new StringBuilder();
        do { Scanner input = new Scanner(System.in);
            System.out.println("Введите целое число :");
            try { number = input.nextInt();
                numberToList = String.valueOf(number).toCharArray();
                for (char numberChar : numberToList) {
                    stack.add(numberChar);
                }
                while (!(stack.isEmpty())) {
                    invertedNumber.append(stack.removeLast());
                }
                System.out.println("Инвертированное число " + invertedNumber);
                input.close();
            }
            catch (InputMismatchException ex) {
                System.out.println("Это не целое число.");
            }
        } while (number == 0);

        System.out.println("\n Задача 5");
        Set <Byte> firstSet = new HashSet<>();
        Set <Byte> secondSet = new HashSet<>();
        for (;firstSet.size() < 8;) {
            firstSet.add((byte)((Math.random()*41)+1));
        }
        for (;secondSet.size() < 8;) {
            secondSet.add((byte)((Math.random()*41)+1));
        }
        System.out.println("Множество А: " + firstSet);
        System.out.println("Множество Б: " + secondSet);
        System.out.println("Пересечения множеств А и Б: " + getIntersectionOfSets(secondSet,firstSet));
        System.out.println("Объединение множеств А и Б: " + mergeSets(firstSet,secondSet));

        System.out.println("\n Задача 6");
        String englishVerse = "The mouse was eaten. By a very bad kitten. The kitten was glad. The mouse felt bad.";
        String [] wordArray = englishVerse.split(" ");
        HashSet <String> wordsSet = new HashSet<>();
        for (String word : wordArray) {
            wordsSet.add(word.toLowerCase().replace(".",""));
        }
        System.out.println("Текст на англиском: " + englishVerse);
        System.out.println("Все различные слова этого текста: " + wordsSet);
    }

    private static Set <Byte> getIntersectionOfSets (Set <Byte> comparedSet, Set <Byte> setWithWhichCompare) {
        Set <Byte> intersectionOfSets = new HashSet<>();
        intersectionOfSets.addAll(setWithWhichCompare);
        intersectionOfSets.retainAll(comparedSet);
        return intersectionOfSets;
    }

    private static Set <Byte> mergeSets (Set <Byte> firstSet, Set <Byte> secondSet) {
        firstSet.addAll(secondSet);
        return firstSet;
    }
}
