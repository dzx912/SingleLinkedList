package org.util.collection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;
import java.util.logging.Logger;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

/**
 * @author Anton Lenok
 * @since 21.10.16.
 */
public class SingleLinkedListTest {

    private static final Logger LOGGER = Logger.getLogger(SingleLinkedListTest.class.getName());

    private List<Integer> linkedListCommon;
    private final static List<Integer> CHECK_ARRAY = asList(5, 4, 3, 2, 1, 5, 6);
    private final static List<Integer> CHECK_ARRAY_BIGGER = asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
    private final static List<Integer> EMPTY_LIST = Collections.emptyList();


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        linkedListCommon = new SingleLinkedList<>(CHECK_ARRAY);
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

    @Test
    public void contains() throws Exception {
        assertTrue(linkedListCommon.contains(5));
        assertTrue(linkedListCommon.contains(1));
        assertTrue(linkedListCommon.contains(6));
        assertFalse(linkedListCommon.contains(7));
        assertFalse(linkedListCommon.contains(null));

        assertFalse(linkedListCommon.contains(EMPTY_LIST));
    }

    @Test
    public void containsStandard() throws Exception {
        assertFalse(CHECK_ARRAY.contains(null));
    }

    @Test
    public void indexOf() throws Exception {
        assertEquals(linkedListCommon.indexOf(5), 0);
        assertEquals(linkedListCommon.indexOf(1), 4);
        assertEquals(linkedListCommon.indexOf(6), 6);
        assertEquals(linkedListCommon.indexOf(7), -1);

        assertEquals(linkedListCommon.indexOf(EMPTY_LIST), -1);
    }

    @Test
    public void lastIndexOf() throws Exception {
        assertEquals(linkedListCommon.lastIndexOf(1), 4);
        assertEquals(linkedListCommon.lastIndexOf(5), 5);
        assertEquals(linkedListCommon.lastIndexOf(6), 6);
        assertEquals(linkedListCommon.lastIndexOf(7), -1);

        assertEquals(linkedListCommon.lastIndexOf(EMPTY_LIST), -1);
    }

    @Test
    public void toArray() throws Exception {
        Object[] convertArray = linkedListCommon.toArray();
        assertEquals(convertArray.length, CHECK_ARRAY.size());
        for (int indexConvertArray = 0; indexConvertArray < convertArray.length; indexConvertArray++) {
            assertTrue(convertArray[indexConvertArray] instanceof Integer);
            assertEquals(convertArray[indexConvertArray].getClass(), Integer.class);
            assertEquals(convertArray[indexConvertArray], CHECK_ARRAY.get(indexConvertArray));
        }
    }

    @Test
    public void toArraySameType() throws Exception {
        Integer[] firstEmptyArray = new Integer[0];
        Integer[] convertArrayEmpty = linkedListCommon.toArray(firstEmptyArray);
        assertEquals(firstEmptyArray.length, 0);
        assertEquals(linkedListCommon.size(), CHECK_ARRAY.size());
        assertEquals(convertArrayEmpty.length, CHECK_ARRAY.size());
        checkingToArraySameType(convertArrayEmpty);

        Integer[] sameSizeArray = new Integer[CHECK_ARRAY.size()];
        Integer[] convertArraySameSize = linkedListCommon.toArray(sameSizeArray);
        assertEquals(sameSizeArray.length, CHECK_ARRAY.size());
        assertEquals(linkedListCommon.size(), CHECK_ARRAY.size());
        assertEquals(convertArraySameSize.length, CHECK_ARRAY.size());
        checkingToArraySameType(convertArraySameSize);

        int biggerSize = CHECK_ARRAY_BIGGER.size();
        Integer[] biggerSizeArray = new Integer[CHECK_ARRAY_BIGGER.size()];
        for (int indexArray = 0; indexArray < CHECK_ARRAY_BIGGER.size(); indexArray++) {
            biggerSizeArray[indexArray] = CHECK_ARRAY_BIGGER.get(indexArray);
        }
        Integer[] convertArrayBiggerSize = linkedListCommon.toArray(biggerSizeArray);
        assertEquals(biggerSizeArray.length, biggerSize);
        assertEquals(linkedListCommon.size(), CHECK_ARRAY.size());
        assertEquals(convertArrayBiggerSize.length, biggerSize);
        checkingToArraySameType(convertArrayBiggerSize);
        for (int indexConvertArray = CHECK_ARRAY.size(); indexConvertArray < linkedListCommon.size(); indexConvertArray++) {
            assertEquals(convertArrayBiggerSize[indexConvertArray].getClass(), Integer.class);
            assertEquals(convertArrayBiggerSize[indexConvertArray], CHECK_ARRAY_BIGGER.get(indexConvertArray));
            assertEquals(biggerSizeArray[indexConvertArray], CHECK_ARRAY_BIGGER.get(indexConvertArray));
        }
    }

    private void checkingToArraySameType(Integer[] convertArray) {
        for (int indexConvertArray = 0; indexConvertArray < CHECK_ARRAY.size(); indexConvertArray++) {
            assertEquals(convertArray[indexConvertArray].getClass(), Integer.class);
            assertEquals(convertArray[indexConvertArray], CHECK_ARRAY.get(indexConvertArray));
            assertEquals(linkedListCommon.get(indexConvertArray), CHECK_ARRAY.get(indexConvertArray));
        }
    }

    @Test
    public void toArraySameTypeStandard() throws Exception {
        List<Integer> intArrayList = new ArrayList<>(CHECK_ARRAY);
        Integer[] firstEmptyArray = new Integer[0];
        assertEquals(firstEmptyArray.length, 0);
        Integer[] convertArrayEmpty = intArrayList.toArray(firstEmptyArray);
        assertEquals(convertArrayEmpty.length, CHECK_ARRAY.size());
        checkingToArraySameTypeStandard(intArrayList, convertArrayEmpty);

        Integer[] sameSizeArray = new Integer[CHECK_ARRAY.size()];
        Integer[] convertArraySameSize = intArrayList.toArray(sameSizeArray);
        assertEquals(sameSizeArray.length, CHECK_ARRAY.size());
        assertEquals(convertArraySameSize.length, CHECK_ARRAY.size());
        assertEquals(intArrayList.size(), CHECK_ARRAY.size());
        checkingToArraySameTypeStandard(intArrayList, convertArraySameSize);

        int biggerSize = CHECK_ARRAY_BIGGER.size();
        Integer[] biggerSizeArray = new Integer[CHECK_ARRAY_BIGGER.size()];
        for (int indexArray = 0; indexArray < CHECK_ARRAY_BIGGER.size(); indexArray++) {
            biggerSizeArray[indexArray] = CHECK_ARRAY_BIGGER.get(indexArray);
        }
        Integer[] convertArrayBiggerSize = intArrayList.toArray(biggerSizeArray);
        assertEquals(biggerSizeArray.length, biggerSize);
        assertEquals(intArrayList.size(), CHECK_ARRAY.size());
        assertEquals(convertArrayBiggerSize.length, biggerSize);
        checkingToArraySameType(convertArrayBiggerSize);
        for (int indexConvertArray = CHECK_ARRAY.size(); indexConvertArray < linkedListCommon.size(); indexConvertArray++) {
            assertEquals(convertArrayBiggerSize[indexConvertArray].getClass(), Integer.class);
            assertEquals(convertArrayBiggerSize[indexConvertArray], CHECK_ARRAY_BIGGER.get(indexConvertArray));
            assertEquals(biggerSizeArray[indexConvertArray], CHECK_ARRAY_BIGGER.get(indexConvertArray));
        }
    }

    private void checkingToArraySameTypeStandard(List<Integer> intArrayList, Integer[] convertArraySameSize) {
        for (int indexConvertArray = 0; indexConvertArray < convertArraySameSize.length; indexConvertArray++) {
            assertEquals(convertArraySameSize[indexConvertArray].getClass(), Integer.class);
            assertEquals(convertArraySameSize[indexConvertArray], CHECK_ARRAY.get(indexConvertArray));
            assertEquals(intArrayList.get(indexConvertArray), CHECK_ARRAY.get(indexConvertArray));
        }
    }

    @Test
    public void remove() throws Exception {
        List<Integer> linkedList = new SingleLinkedList<>();

        thrown.expect(IndexOutOfBoundsException.class);
        linkedList.remove(0);

        linkedList.addAll(CHECK_ARRAY);

        Integer removed;

        removed = linkedList.remove(1);
        assertEquals(removed, new Integer(4));
        assertList(linkedList, asList(5, 3, 2, 1, 5, 6));

        removed = linkedList.remove(0);
        assertEquals(removed, new Integer(5));
        assertList(linkedList, asList(3, 2, 1, 5, 6));

        removed = linkedList.remove(4);
        assertEquals(removed, new Integer(6));
        assertList(linkedList, asList(3, 2, 1, 5));

        linkedList.remove(7);

        removed = linkedList.remove(2);
        assertEquals(removed, new Integer(1));
        assertList(linkedList, asList(3, 2, 5));

        removed = linkedList.remove(1);
        assertEquals(removed, new Integer(2));
        assertList(linkedList, asList(3, 5));

        removed = linkedList.remove(1);
        assertEquals(removed, new Integer(5));
        assertList(linkedList, singletonList(3));

        removed = linkedList.remove(0);
        assertEquals(removed, new Integer(3));
        assertList(linkedList, EMPTY_LIST);

        linkedList.remove(0);
    }

    @Test
    public void removeAdd() throws Exception {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);
        linkedList.remove(linkedList.size() - 1);
        assertList(linkedList, asList(5, 4, 3, 2, 1, 5));

        linkedList.add(7);
        assertList(linkedList, asList(5, 4, 3, 2, 1, 5, 7));

        linkedList = new SingleLinkedList<>(CHECK_ARRAY);
        linkedList.remove(0);
        assertList(linkedList, asList(4, 3, 2, 1, 5, 6));

        linkedList.add(7);
        assertList(linkedList, asList(4, 3, 2, 1, 5, 6, 7));
    }

    @Test
    public void removeObject() throws Exception {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        boolean isRemoved;

        isRemoved = linkedList.remove(new Integer(4));
        assertEquals(isRemoved, true);
        assertList(linkedList, asList(5, 3, 2, 1, 5, 6));

        isRemoved = linkedList.remove(new Integer(5));
        assertEquals(isRemoved, true);
        assertList(linkedList, asList(3, 2, 1, 6));

        isRemoved = linkedList.remove(new Integer(6));
        assertEquals(isRemoved, true);
        assertList(linkedList, asList(3, 2, 1));

        isRemoved = linkedList.remove(new Integer(5));
        assertEquals(isRemoved, false);
        assertList(linkedList, asList(3, 2, 1));

        isRemoved = linkedList.remove("Wrong type");
        assertEquals(isRemoved, false);
        assertList(linkedList, asList(3, 2, 1));

        isRemoved = linkedList.remove(new Integer(3));
        assertEquals(isRemoved, true);
        assertList(linkedList, asList(2, 1));

        isRemoved = linkedList.remove(new Integer(1));
        assertEquals(isRemoved, true);
        assertList(linkedList, singletonList(2));

        isRemoved = linkedList.remove(new Integer(2));
        assertEquals(isRemoved, true);
        assertList(linkedList, EMPTY_LIST);
    }

    private void assertList(List<Integer> linkedList, List<Integer> checkArray) {
        LOGGER.info("List for checking: " + linkedList);
        LOGGER.info("Right list:        " + checkArray);
        assertEquals("Wrong size list", linkedList.size(), checkArray.size());
        Iterator<Integer> iterator = linkedList.iterator();
        int indexRemovedArray;
        for (indexRemovedArray = 0; iterator.hasNext(); indexRemovedArray++) {
            Integer element = iterator.next();
            assertEquals(element, checkArray.get(indexRemovedArray));
        }
        assertEquals("Wrong real size list", indexRemovedArray, checkArray.size());
    }

    @Test
    public void testAssertList() throws Exception {
        assertList(new SingleLinkedList<>(asList(1, 2)), new SingleLinkedList<>(asList(1, 2)));

        assertList(new SingleLinkedList<>(asList(2, 2)), new SingleLinkedList<>(asList(2, 2)));
    }

    @Test
    public void containsAll() throws Exception {
        assertTrue(linkedListCommon.containsAll(asList(1, 2, 3)));
        assertFalse(linkedListCommon.containsAll(asList(1, 2, 3, 7)));
    }

    @Test
    public void addAll() throws Exception {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        assertFalse(linkedList.addAll(EMPTY_LIST));
        assertList(linkedList, CHECK_ARRAY);

        assertTrue(linkedList.addAll(asList(0, 9, 8, 7)));
        assertList(linkedList, asList(5, 4, 3, 2, 1, 5, 6, 0, 9, 8, 7));
    }

    @Test
    public void addAllIndex() throws Exception {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        assertTrue(linkedList.addAll(0, EMPTY_LIST));
        assertList(linkedList, CHECK_ARRAY);

        assertTrue(linkedList.addAll(CHECK_ARRAY.size() - 1, asList(0, 9, 8, 7)));
        assertList(linkedList, asList(5, 4, 3, 2, 1, 5, 6, 0, 9, 8, 7));

        assertTrue(linkedList.addAll(2, asList(11, 12, 13, 14)));
        assertList(linkedList, asList(5, 4, 3, 11, 12, 13, 14, 2, 1, 5, 6, 0, 9, 8, 7));

        thrown.expect(IndexOutOfBoundsException.class);
        assertTrue(linkedList.addAll(linkedList.size(), asList(0, 9, 8, 7)));
    }

    @Test
    public void removeAll() throws Exception {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);
        assertTrue(linkedList.removeAll(singletonList(5)));
        assertList(linkedList, asList(4, 3, 2, 1, 6));

        linkedList = new SingleLinkedList<>(CHECK_ARRAY);
        assertTrue(linkedList.removeAll(singletonList(6)));
        assertList(linkedList, asList(5, 4, 3, 2, 1, 5));

        linkedList = new SingleLinkedList<>(CHECK_ARRAY);
        assertTrue(linkedList.removeAll(asList(4, 3, 1)));
        assertList(linkedList, asList(5, 2, 5, 6));

        linkedList = new SingleLinkedList<>(CHECK_ARRAY);
        assertTrue(linkedList.removeAll(CHECK_ARRAY));
        assertList(linkedList, EMPTY_LIST);

        linkedList = new SingleLinkedList<>(CHECK_ARRAY);
        assertFalse(linkedList.removeAll(asList(0, 9, 8, 7)));
        assertList(linkedList, CHECK_ARRAY);
    }

    @Test
    public void removeAllStandard() throws Exception {
        List<Integer> intArrayList = new ArrayList<>(CHECK_ARRAY);
        intArrayList.removeAll(singletonList(5));
        assertList(intArrayList, asList(4, 3, 2, 1, 6));
    }

    @Test
    public void retainAll() throws Exception {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);
        assertTrue(linkedList.retainAll(asList(1, 2, 3, 5)));
        assertList(linkedList, asList(5, 3, 2, 1, 5));

        linkedList.addAll(asList(2, 3, 4, 5));
        assertList(linkedList, asList(5, 3, 2, 1, 5, 2, 3, 4, 5));

        linkedList.add(7);
        assertList(linkedList, asList(5, 3, 2, 1, 5, 2, 3, 4, 5, 7));

        linkedList = new SingleLinkedList<>(CHECK_ARRAY);
        assertFalse(linkedList.retainAll(CHECK_ARRAY));
        assertList(linkedList, CHECK_ARRAY);

        linkedList = new SingleLinkedList<>(CHECK_ARRAY);
        assertTrue(linkedList.retainAll(EMPTY_LIST));
        assertList(linkedList, EMPTY_LIST);

        linkedList = new SingleLinkedList<>(CHECK_ARRAY);
        assertTrue(linkedList.retainAll(asList(7, 8, 9, 10)));
        assertList(linkedList, EMPTY_LIST);

        linkedList.add(1);
        linkedList.add(2);
        assertList(linkedList, asList(1, 2));

        linkedList = new SingleLinkedList<>(CHECK_ARRAY);
        assertTrue(linkedList.retainAll(asList(8, 1, 9, 10)));
        assertList(linkedList, singletonList(1));

        linkedList.addAll(asList(2, 3, 4, 5));
        assertList(linkedList, asList(1, 2, 3, 4, 5));
    }

    @Test
    public void subList() throws Exception {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);
        int size = linkedList.size();
        assertList(linkedList.subList(0, size), CHECK_ARRAY);
        assertList(linkedList.subList(1, 1), EMPTY_LIST);
        assertList(linkedList.subList(1, 3), asList(4, 3));
        assertList(linkedList.subList(1, 2), singletonList(4));

        assertList(linkedList.subList(size, size), EMPTY_LIST);

        thrown.expect(IllegalArgumentException.class);
        assertList(linkedList.subList(2, 1), EMPTY_LIST);

        thrown.expect(IndexOutOfBoundsException.class);
        assertList(linkedList.subList(0, size + 1), EMPTY_LIST);
        assertList(linkedList.subList(-1, size), EMPTY_LIST);
    }

    @Test
    public void subListStandard() throws Exception {
        List<Integer> linkedList = new ArrayList<>(CHECK_ARRAY);
        int size = linkedList.size();
        assertList(linkedList.subList(0, size), CHECK_ARRAY);
        assertList(linkedList.subList(1, 1), EMPTY_LIST);
        assertList(linkedList.subList(1, 3), asList(4, 3));
        assertList(linkedList.subList(1, 2), singletonList(4));

        assertList(linkedList.subList(size, size), EMPTY_LIST);

        thrown.expect(IllegalArgumentException.class);
        assertList(linkedList.subList(2, 1), EMPTY_LIST);

        thrown.expect(IndexOutOfBoundsException.class);
        assertList(linkedList.subList(0, size + 1), EMPTY_LIST);
        assertList(linkedList.subList(-1, size), EMPTY_LIST);
    }

    @Test
    public void testToString() throws Exception {
        List<Integer> listStandard = new ArrayList<>(CHECK_ARRAY);
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);
        assertEquals(linkedList.toString(), listStandard.toString());
    }

    @Test
    public void listIterator() throws Exception {

    }

    @Test
    public void listIteratorStandard() throws Exception {

    }
}