package org.sberbank;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Anton Lenok
 * @since 21.10.16.
 */
public class SingleLinkedListTest {
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
    }

}