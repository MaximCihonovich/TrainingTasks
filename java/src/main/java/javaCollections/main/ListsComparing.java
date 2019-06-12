package javaCollections.main;

import java.util.*;

class ListsComparing {

    private ArrayList <Byte> arrayList = new ArrayList<>();
    private LinkedList <Byte> linkedList = new LinkedList<>();

    ListsComparing(int sizeOfList) {
        byte [] numbers = new byte[sizeOfList];
        new Random().nextBytes(numbers);
        for (byte number : numbers) {
            arrayList.add(number);
        }
        for (byte number : numbers) {
            linkedList.add(number);
        }
        System.out.println("Сравниваемый массив чисел: " + arrayList);
    }

    String compareLists() {
        boolean removeFlag = false;
        long iterationBegin, timeArrayListIteration, timeLinkedListIteration;
        ListIterator <Byte> listIterator;
        listIterator = arrayList.listIterator();
        iterationBegin = System.nanoTime();
        do {
            while (listIterator.hasNext()) {
                listIterator.next();
                if (removeFlag) {
                    listIterator.remove();
                    removeFlag = false;
                } else {
                    removeFlag = true;
                }
            }
            while (listIterator.previousIndex() != -1) {
                listIterator.previous();
                if (removeFlag) {
                    listIterator.remove();
                    removeFlag = false;
                } else {
                    removeFlag = true;
                }
            }
        }
        while (listIterator.hasNext() || listIterator.previousIndex() != -1);
        timeArrayListIteration = System.nanoTime() - iterationBegin;
        removeFlag = false;
        listIterator = linkedList.listIterator();
        iterationBegin = System.nanoTime();
        do {
            while (listIterator.hasNext()) {
                listIterator.next();
                if (removeFlag) {
                    listIterator.remove();
                    removeFlag = false;
                } else {
                    removeFlag = true;
                }
            }
            while (listIterator.previousIndex() != -1) {
                listIterator.previous();
                if (removeFlag) {
                    listIterator.remove();
                    removeFlag = false;
                } else {
                    removeFlag = true;
                }
            }
        }
        while (listIterator.hasNext() || listIterator.previousIndex() != -1);
        timeLinkedListIteration = System.nanoTime() - iterationBegin;
        return timeArrayListIteration > timeLinkedListIteration ?
                String.format("LinkedList (%d ns) быстрее ArrayList (%d ns)",timeLinkedListIteration,timeArrayListIteration):
                String.format("ArrayList (%d ns) быстрее LinkedList (%d ns)",timeArrayListIteration,timeLinkedListIteration);
    }
}
