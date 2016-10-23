package org.sberbank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Anton Lenok on 02.10.16.
 */
public class MainApp {

    public static void main(String[] args) {
        List<Integer> linkedList = new SingleLinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        linkedList.stream().forEach(value -> System.out.println(value));

        ArrayList<Integer> intArrayList = new ArrayList<>();
        intArrayList.add(1);
        intArrayList.add(2);
        intArrayList.add(3);

        Integer[] intArray = new Integer[]{4, 5, 6, 7, 8, 9, 10, 11, 1, 2, 3};

        Integer[] integers = intArrayList.toArray(intArray);
        System.out.println("---");
        Arrays.stream(integers).forEach(value -> System.out.println(value));
        System.out.println("---");
        Arrays.stream(intArray).forEach(value -> System.out.println(value));
        System.out.println("---");
        intArrayList.forEach(value -> System.out.println(value));
    }
}
