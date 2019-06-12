package javaCollections.main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainTaskRunner {
    public static void main(String[] args) {

        int listSize, itemAnchor, parkingSize;
        byte menuItem, parkingPlace;
        boolean nextTaskFlag = true;
        Scanner inputStream = new Scanner(System.in);

        System.out.println(" Задача 1");
        do { System.out.println("Введите размер сравниваемых списков:");
            try { listSize = inputStream.nextInt();
                ListsComparing listCompare = new ListsComparing(listSize);
                System.out.println(listCompare.compareLists());
                nextTaskFlag = false;
            } catch (InputMismatchException ex) {
                System.out.println("Это не целое число.");
            }
        } while (nextTaskFlag);

        System.out.println("\n Задача 2");
        do { System.out.println("Введите размер массива чиcел:");
            try { listSize = inputStream.nextInt();
                ItemsMovement numbersList = new ItemsMovement(listSize);
                System.out.println("Массив чисел:" + '\n' + numbersList.toString());
                System.out.println("Введите номер элемента вокруг которого будет происходить перестановка:");
                try { itemAnchor = inputStream.nextInt()-1;
                    if (itemAnchor <= listSize) {
                        System.out.println("Элемент " + '\"' + "якорь" + '\"' + " : " + numbersList.getItemAnchor(itemAnchor));
                        numbersList.moveListItems(itemAnchor);
                        System.out.println("Массив чисел:" + '\n' + numbersList.toString());
                        nextTaskFlag = true;
                    } else {
                        System.out.println("Введите число не превышающее количество элементов.");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Это не целое число.");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Это не целое число.");
            }
        } while (!(nextTaskFlag));

        System.out.println("\n Задача 3");
        do {System.out.println("Введите размер парковки:");
        try { parkingSize = inputStream.nextInt();
            Parking parking = new Parking(parkingSize);
            do { System.out.println("Выберите действие:" + '\n' +
                    "1. Вывести список мест парковки." + '\n' +
                    "2. Поставить машину на парковку." + '\n' +
                    "3. Убрать машину с парковки." + '\n' +
                    "4. Завершить работу." + '\n');
                try { menuItem = inputStream.nextByte();
                    switch (menuItem) {
                        case 1 :
                            System.out.println(parking.toString());
                            break;
                        case 2 :
                            parking.addCarToParking();
                            break;
                        case 3 :
                            System.out.println("Введите номер освобождаемого места.");
                            try { parkingPlace = inputStream.nextByte();
                                parking.removeCarFromParking(parkingPlace);
                                break;
                            } catch (InputMismatchException ex) {
                                System.out.println("Это не целое число.");
                            }
                        case 4 :
                            nextTaskFlag = false;
                            break;
                        default : System.out.println("Введите число от 1 до 4.");
                    }
                } catch (InputMismatchException ex) {
                System.out.println("Это не целое число.");
                }
            } while (nextTaskFlag);
        } catch (InputMismatchException ex) {
            System.out.println("Это не целое число.");
        }
        } while (nextTaskFlag);
        inputStream.close();
    }
}
