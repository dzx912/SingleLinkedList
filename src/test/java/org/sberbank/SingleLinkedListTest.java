package org.sberbank;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Anton Lenok
 * @since 21.10.16.
 */
public class SingleLinkedListTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
        List<Integer> linkedList = new SingleLinkedList<>();
        linkedList.add(5);
        linkedList.add(4);
        linkedList.add(3);
        linkedList.add(2);
        linkedList.add(1);

        assertEquals(linkedList.get(4), new Integer(1));
        assertEquals(linkedList.get(3), new Integer(2));
        assertEquals(linkedList.get(2), new Integer(3));
        assertEquals(linkedList.get(1), new Integer(4));
        assertEquals(linkedList.get(0), new Integer(5));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException() {
        new SingleLinkedList<>().get(0);
    }

    @Test
    public void testOutOfBounds() {
        List<Integer> linkedList = new SingleLinkedList<>();

        thrown.expect(IndexOutOfBoundsException.class);

        //First exception
        linkedList.get(0);

        linkedList.add(5);
        linkedList.add(4);
        linkedList.add(3);
        linkedList.add(2);
        linkedList.add(1);

        //Second exception
        linkedList.get(5);

        //Third exception
        linkedList.get(-1);
    }

    @Test
    public void testSet() {
        List<Integer> linkedList = new SingleLinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);

        assertEquals(linkedList.get(0), new Integer(1));
        linkedList.set(0, 6);
        assertEquals(linkedList.get(0), new Integer(6));

        assertEquals(linkedList.get(4), new Integer(5));
        linkedList.set(4, 7);
        assertEquals(linkedList.get(4), new Integer(7));
    }

}