package org.sberbank;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Anton Lenok
 * @since 21.10.16.
 */
public class SingleLinkedListTest {

    List<Integer> linkedListComman;


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        linkedListComman = new SingleLinkedList<>();
        linkedListComman.add(5);
        linkedListComman.add(4);
        linkedListComman.add(3);
        linkedListComman.add(2);
        linkedListComman.add(1);
    }

    @Test
    public void contains() throws Exception {
        assertTrue(linkedListComman.contains(1));
        assertFalse(linkedListComman.contains(6));

        assertFalse(linkedListComman.contains(new ArrayList<>()));
    }

    @Test
    public void indexOf() throws Exception {
        assertEquals(linkedListComman.indexOf(1), 4);
        assertEquals(linkedListComman.indexOf(5), 0);
        assertEquals(linkedListComman.indexOf(6), -1);

        assertEquals(linkedListComman.indexOf(new ArrayList<>()), -1);
    }

    @Test
    public void size() throws Exception {
        List<Integer> linkedList = new SingleLinkedList<>();
        assertTrue(linkedList.isEmpty());
        assertEquals(linkedList.size(), 0);

        linkedList.add(1);
        assertFalse(linkedList.isEmpty());
        assertEquals(linkedList.size(), 1);

        linkedList.add(1);
        assertFalse(linkedList.isEmpty());
        assertEquals(linkedList.size(), 2);

        linkedList.add(1);
        assertFalse(linkedList.isEmpty());
        assertEquals(linkedList.size(), 3);

        linkedList.clear();
        assertTrue(linkedList.isEmpty());
        assertEquals(linkedList.size(), 0);

        linkedList.add(1);
        assertFalse(linkedList.isEmpty());
        assertEquals(linkedList.size(), 1);
    }

    @Test
    public void get() throws Exception {
        assertEquals(linkedListComman.get(4), new Integer(1));
        assertEquals(linkedListComman.get(3), new Integer(2));
        assertEquals(linkedListComman.get(2), new Integer(3));
        assertEquals(linkedListComman.get(1), new Integer(4));
        assertEquals(linkedListComman.get(0), new Integer(5));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException() {
        new SingleLinkedList<>().get(0);
    }

    @Test
    public void testOutOfBounds() {
        thrown.expect(IndexOutOfBoundsException.class);

        linkedListComman.get(5);
        linkedListComman.get(-1);
    }

    @Test
    public void testSet() {
        List<Integer> linkedList = new SingleLinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        assertEquals(linkedList.get(0), new Integer(1));
        linkedList.set(0, 6);
        assertEquals(linkedList.get(0), new Integer(6));

        assertEquals(linkedList.get(2), new Integer(3));
        linkedList.set(2, 7);
        assertEquals(linkedList.get(2), new Integer(7));

        thrown.expect(IndexOutOfBoundsException.class);
        linkedList.set(3, 7);
    }

}