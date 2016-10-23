package org.sberbank;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Anton Lenok
 * @since 21.10.16.
 */
public class SingleLinkedListTest {

    List<Integer> linkedListCommon;
    private final static List<Integer> CHECK_ARRAY = Arrays.asList(5, 4, 3, 2, 1, 5, 6);
    private final static List<Integer> CHECK_ARRAY_BIGGER = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        linkedListCommon = new SingleLinkedList<>();
        CHECK_ARRAY.stream().forEach(value -> linkedListCommon.add(value));
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
    public void toArraySameTypeCheck() throws Exception {

        List<Integer> intArrayList = new ArrayList<>();
        intArrayList.addAll(CHECK_ARRAY);
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
        CHECK_ARRAY.stream().forEach(value -> linkedList.add(value));

        linkedList.remove(1);
        checkRemovedList(linkedList, Arrays.asList(5, 3, 2, 1, 5, 6));

        linkedList.remove(0);
        checkRemovedList(linkedList, Arrays.asList(3, 2, 1, 5, 6));

        linkedList.remove(4);
        checkRemovedList(linkedList, Arrays.asList(3, 2, 1, 5));

        thrown.expect(IndexOutOfBoundsException.class);
        linkedList.remove(7);

        linkedList.remove(2);
        checkRemovedList(linkedList, Arrays.asList(3, 2, 5));

        linkedList.remove(1);
        checkRemovedList(linkedList, Arrays.asList(3, 5));

        linkedList.remove(1);
        checkRemovedList(linkedList, Collections.singletonList(3));

        linkedList.remove(0);
        checkRemovedList(linkedList, new ArrayList<>());
    }

    private void checkRemovedList(List<Integer> linkedList, List<Integer> checkArray) {
        assertEquals(linkedList.size(), checkArray.size());
        Iterator<Integer> iterator = linkedList.iterator();
        for (int indexRemovedArray = 0; iterator.hasNext(); indexRemovedArray++) {
            Integer element = iterator.next();
            assertEquals(element, checkArray.get(indexRemovedArray));
        }
    }
}