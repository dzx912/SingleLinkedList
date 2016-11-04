package org.util.collection;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by Anton Lenok on 02.10.16.
 * Main app with example how to use SingleLinkedList
 */
public class MainApp {

    public static void main(String[] args) {
        List<Integer> linkedList = new SingleLinkedList<>(asList(1, 2, 3));

        linkedList.forEach(System.out::println);
    }
}
