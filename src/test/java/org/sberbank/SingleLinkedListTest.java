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

    List<Integer> linkedListCommon;


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        linkedListCommon = new SingleLinkedList<>();
        linkedListCommon.add(5);
        linkedListCommon.add(4);
        linkedListCommon.add(3);
        linkedListCommon.add(2);
        linkedListCommon.add(1);
        linkedListCommon.add(5);
        linkedListCommon.add(6);
    }

    @Test
    public void contains() throws Exception {
        assertTrue(linkedListCommon.contains(5));
        assertTrue(linkedListCommon.contains(1));
        assertTrue(linkedListCommon.contains(6));
        assertFalse(linkedListCommon.contains(7));

        assertFalse(linkedListCommon.contains(new ArrayList<>()));
    }

    @Test
    public void indexOf() throws Exception {
        assertEquals(linkedListCommon.indexOf(5), 0);
        assertEquals(linkedListCommon.indexOf(1), 4);
        assertEquals(linkedListCommon.indexOf(6), 6);
        assertEquals(linkedListCommon.indexOf(7), -1);

        assertEquals(linkedListCommon.indexOf(new ArrayList<>()), -1);
    }

    @Test
    public void lastIndexOf() throws Exception {
        assertEquals(linkedListCommon.lastIndexOf(1), 4);
        assertEquals(linkedListCommon.lastIndexOf(5), 5);
        assertEquals(linkedListCommon.lastIndexOf(6), 6);
        assertEquals(linkedListCommon.lastIndexOf(7), -1);

        assertEquals(linkedListCommon.lastIndexOf(new ArrayList<>()), -1);
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
        assertEquals(linkedListCommon.get(5), new Integer(5));
        assertEquals(linkedListCommon.get(4), new Integer(1));
        assertEquals(linkedListCommon.get(3), new Integer(2));
        assertEquals(linkedListCommon.get(2), new Integer(3));
        assertEquals(linkedListCommon.get(1), new Integer(4));
        assertEquals(linkedListCommon.get(0), new Integer(5));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException() {
        new SingleLinkedList<>().get(0);
    }

    @Test
    public void testOutOfBounds() throws Exception {
        thrown.expect(IndexOutOfBoundsException.class);

        linkedListCommon.get(7);
        linkedListCommon.get(-1);
    }

    @Test
    public void testSet() throws Exception {
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