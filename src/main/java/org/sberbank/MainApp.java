package org.sberbank;

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

//        for (Integer value : linkedList) {
//            System.out.println(value);
//        }

        linkedList.stream().forEach(value -> System.out.println(value));
    }
}
