package org.util.collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

/**
 * Unit tests for SingleLinkedList
 */
public class SingleLinkedListTest {

    private static final Logger LOGGER = LogManager.getLogger(SingleLinkedListTest.class);

    private List<Integer> linkedListCommon;
    private static final List<Integer> CHECK_ARRAY = asList(5, 4, 3, 2, 1, 5, 6);
    private static final List<Integer> CHECK_ARRAY_BIGGER = asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
    private static final List<Integer> EMPTY_LIST = Collections.emptyList();


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        linkedListCommon = new SingleLinkedList<>(CHECK_ARRAY);
    }

    @Test
    public void afterCreateShouldBeEmpty() {
        List<Integer> linkedList = new SingleLinkedList<>();
        assertTrue(linkedList.isEmpty());
        assertEquals(0, linkedList.size());
    }

    @Test
    public void afterAddShouldBeNotEmpty() {
        List<Integer> linkedList = new SingleLinkedList<>();
        linkedList.add(1);

        assertFalse(linkedList.isEmpty());
        assertEquals(1, linkedList.size());
    }

    @Test
    public void afterDoubleAddShouldBeNotEmpty() {
        List<Integer> linkedList = new SingleLinkedList<>();
        linkedList.add(1);
        linkedList.add(1);

        assertFalse(linkedList.isEmpty());
        assertEquals(2, linkedList.size());
    }

    @Test
    public void afterCreateAndClearShouldBeEmpty() {
        List<Integer> linkedList = new SingleLinkedList<>();

        linkedList.clear();

        assertTrue(linkedList.isEmpty());
        assertEquals(0, linkedList.size());
    }

    @Test
    public void afterAddAndClearShouldBeEmpty() {
        List<Integer> linkedList = new SingleLinkedList<>();

        linkedList.add(1);
        linkedList.clear();

        assertTrue(linkedList.isEmpty());
        assertEquals(0, linkedList.size());
    }

    @Test
    public void afterInitShouldGetCorrectValue() {
        assertEquals(CHECK_ARRAY.get(5), linkedListCommon.get(5));
        assertEquals(CHECK_ARRAY.get(4), linkedListCommon.get(4));
        assertEquals(CHECK_ARRAY.get(3), linkedListCommon.get(3));
        assertEquals(CHECK_ARRAY.get(2), linkedListCommon.get(2));
        assertEquals(CHECK_ARRAY.get(1), linkedListCommon.get(1));
        assertEquals(CHECK_ARRAY.get(0), linkedListCommon.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void withoutInitGetShouldThrowException() {
        new SingleLinkedList<>().get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void tooManyIndexShouldThrowException() {
        linkedListCommon.get(7);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void tooLittleIndexShouldThrowException() {
        linkedListCommon.get(-1);
    }

    @Test
    public void afterFirstSetShouldCorrectValue() {
        List<Integer> linkedList = initListWithThreeElement();

        linkedList.set(0, 6);

        assertEquals(new Integer(6), linkedList.get(0));
    }

    @Test
    public void afterSecondSetShouldCorrectValue() {
        List<Integer> linkedList = initListWithThreeElement();

        linkedList.set(2, 7);
        assertEquals(new Integer(7), linkedList.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void afterSetTooManyIndexShouldThrowException() {
        List<Integer> linkedList = initListWithThreeElement();

        linkedList.set(3, 7);
    }

    private List<Integer> initListWithThreeElement() {
        List<Integer> linkedList = new SingleLinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        return linkedList;
    }

    @Test
    public void containsShouldReturnTrue() {
        for (Integer value : CHECK_ARRAY) {
            assertTrue(linkedListCommon.contains(value));
        }
    }

    @Test
    public void containsWithUnknownValueShouldReturnFalse() {
        assertFalse(linkedListCommon.contains(7));
    }

    @Test
    public void containsWithNullShouldReturnFalse() {
        assertFalse(linkedListCommon.contains(null));
    }

    @Test
    public void containsWithEmptyListShouldReturnFalse() {
        assertFalse(linkedListCommon.contains(EMPTY_LIST));
    }

    @Test
    public void indexOfWithCorrectValueShouldFound() {
        assertEquals(0, linkedListCommon.indexOf(5));
        assertEquals(4, linkedListCommon.indexOf(1));
        assertEquals(6, linkedListCommon.indexOf(6));
    }

    @Test
    public void indexOfWithUnknownValueShouldNotFound() {
        assertEquals(-1, linkedListCommon.indexOf(7));
    }

    @Test
    public void indexOfWithEmptyListValueShouldNotFound() {
        assertEquals(-1, linkedListCommon.indexOf(EMPTY_LIST));
    }

    @Test
    public void lastIndexOfWithCorrectValueShouldFound() {
        assertEquals(4, linkedListCommon.lastIndexOf(1));
        assertEquals(5, linkedListCommon.lastIndexOf(5));
        assertEquals(6, linkedListCommon.lastIndexOf(6));
    }

    @Test
    public void lastIndexOfWithUnknownValueShouldNotFound() {
        assertEquals(-1, linkedListCommon.lastIndexOf(7));
    }

    @Test
    public void lastIndexOfWithEmptyListValueShouldNotFound() {
        assertEquals(-1, linkedListCommon.lastIndexOf(EMPTY_LIST));
    }

    @Test
    public void toArrayShouldReturnCorrectSize() {
        Object[] convertArray = linkedListCommon.toArray();
        assertEquals(CHECK_ARRAY.size(), convertArray.length);
    }

    @Test
    public void toArrayShouldReturnCorrectArray() {
        Object[] convertArray = linkedListCommon.toArray();
        for (int indexConvertArray = 0; indexConvertArray < convertArray.length; indexConvertArray++) {
            assertTrue(convertArray[indexConvertArray] instanceof Integer);
            assertEquals(Integer.class, convertArray[indexConvertArray].getClass());
            assertEquals(CHECK_ARRAY.get(indexConvertArray), convertArray[indexConvertArray]);
        }
    }

    @Test
    public void toEmptyArrayShouldReturnSameArray() {
        Integer[] emptyArray = new Integer[0];

        Integer[] convertArrayEmpty = linkedListCommon.toArray(emptyArray);

        assertEquals(0, emptyArray.length);
        assertEquals(CHECK_ARRAY.size(), linkedListCommon.size());
        assertEquals(CHECK_ARRAY.size(), convertArrayEmpty.length);
        checkingToArraySameType(convertArrayEmpty);
    }

    @Test
    public void toArrayWithLessSizeShouldReturnSameArray() {
        Integer[] lessSizeArray = new Integer[1];
        Integer[] convertArraySameSize = linkedListCommon.toArray(lessSizeArray);

        assertEquals(1, lessSizeArray.length);
        assertEquals(CHECK_ARRAY.size(), linkedListCommon.size());
        assertEquals(CHECK_ARRAY.size(), convertArraySameSize.length);
        checkingToArraySameType(convertArraySameSize);
    }

    @Test
    public void toArrayWithSameSizeShouldReturnSameArray() {
        Integer[] sameSizeArray = new Integer[CHECK_ARRAY.size()];
        Integer[] convertArraySameSize = linkedListCommon.toArray(sameSizeArray);

        assertEquals(CHECK_ARRAY.size(), sameSizeArray.length);
        assertEquals(CHECK_ARRAY.size(), linkedListCommon.size());
        assertEquals(CHECK_ARRAY.size(), convertArraySameSize.length);
        checkingToArraySameType(convertArraySameSize);
    }

    @Test
    public void toArrayWithBiggerSizeShouldReturnMixtureArray() {
        int biggerSize = CHECK_ARRAY_BIGGER.size();
        Integer[] biggerSizeArray = new Integer[CHECK_ARRAY_BIGGER.size()];
        for (int indexArray = 0; indexArray < CHECK_ARRAY_BIGGER.size(); indexArray++) {
            biggerSizeArray[indexArray] = CHECK_ARRAY_BIGGER.get(indexArray);
        }

        Integer[] convertArrayBiggerSize = linkedListCommon.toArray(biggerSizeArray);

        assertEquals(biggerSize, biggerSizeArray.length);
        assertEquals(CHECK_ARRAY.size(), linkedListCommon.size());
        assertEquals(biggerSize, convertArrayBiggerSize.length);
        checkingToArraySameType(convertArrayBiggerSize);
        checkLastPartBiggestArrayDidNotChange(biggerSizeArray, convertArrayBiggerSize);
    }

    private void checkingToArraySameType(Integer[] convertArray) {
        for (int index = 0; index < CHECK_ARRAY.size(); index++) {
            assertEquals(Integer.class, convertArray[index].getClass());
            assertEquals(CHECK_ARRAY.get(index), convertArray[index]);
            assertEquals(CHECK_ARRAY.get(index), linkedListCommon.get(index));
        }
    }

    private void checkLastPartBiggestArrayDidNotChange(Integer[] biggerSizeArray, Integer[] convertArrayBiggerSize) {
        for (int indexConvertArray = CHECK_ARRAY.size(); indexConvertArray < linkedListCommon.size(); indexConvertArray++) {
            assertEquals(Integer.class, convertArrayBiggerSize[indexConvertArray].getClass());
            assertEquals(CHECK_ARRAY_BIGGER.get(indexConvertArray), convertArrayBiggerSize[indexConvertArray]);
            assertEquals(CHECK_ARRAY_BIGGER.get(indexConvertArray), biggerSizeArray[indexConvertArray]);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeEmptyListShouldThrowException() {
        List<Integer> linkedList = new SingleLinkedList<>();

        linkedList.remove(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeTooBiggerIndexShouldThrowException() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        linkedList.remove(linkedList.size());
    }

    @Test
    public void afterRemoveElementByIndexShouldCutList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        Integer removed = linkedList.remove(1);

        assertEquals(new Integer(4), removed);
        assertList(asList(5, 3, 2, 1, 5, 6), linkedList);
    }

    @Test
    public void afterTwiceRemoveElementByIndexShouldCutList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        linkedList.remove(1);
        Integer removed = linkedList.remove(0);

        assertEquals(new Integer(5), removed);
        assertList(asList(3, 2, 1, 5, 6), linkedList);
    }

    @Test
    public void afterRemoveThreeNotOrderElementByIndexShouldCorrectList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        linkedList.remove(1);
        linkedList.remove(0);
        Integer removed = linkedList.remove(4);

        assertEquals(new Integer(6), removed);
        assertList(asList(3, 2, 1, 5), linkedList);
    }

    @Test
    public void afterRemoveAllElementsByIndexShouldBeEmptyList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        for (int index = CHECK_ARRAY.size() - 1; 0 <= index; index--) {
            linkedList.remove(index);
        }

        assertList(linkedList, EMPTY_LIST);
    }

    @Test
    public void afterRemoveAllElementsByIndexByRandomOrderShouldBeEmptyList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        IntUnaryOperator reverseIndex = index -> linkedList.size() - index;
        Random random = new Random();
        List<Integer> randomIndices = IntStream.range(0, linkedList.size())
                .map(reverseIndex)
                .map(random::nextInt)
                .boxed()
                .collect(Collectors.toList());

        LOGGER.info("Random indices: " + randomIndices);

        for (int index : randomIndices) {
            LOGGER.info("Remove index: " + index);
            linkedList.remove(index);
        }

        assertList(linkedList, EMPTY_LIST);
    }

    @Test
    public void afterRemoveAllFirstElementsByIndexShouldBeEmptyList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        for (int index = 0; index < CHECK_ARRAY.size(); index++) {
            linkedList.remove(0);
        }

        assertList(linkedList, EMPTY_LIST);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void afterRemoveTooManyElementsByIndexShouldThrowException() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        for (int index = CHECK_ARRAY.size() - 1; 0 <= index; index--) {
            linkedList.remove(index);
        }

        linkedList.remove(0);
    }

    @Test
    public void afterRemoveLastElementByIndexAndAddShouldBeCorrectList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        linkedList.remove(linkedList.size() - 1);
        linkedList.add(7);

        assertList(asList(5, 4, 3, 2, 1, 5, 7), linkedList);
    }

    @Test
    public void afterRemoveFirstElementByIndexAndAddShouldBeCorrectList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        linkedList.remove(0);
        linkedList.add(7);

        assertList(asList(4, 3, 2, 1, 5, 6, 7), linkedList);
    }

    @Test
    public void afterRemoveElementByValueShouldCutList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        boolean isRemoved = linkedList.remove(new Integer(4));

        assertTrue(isRemoved);
        assertList(linkedList, asList(5, 3, 2, 1, 5, 6));
    }

    @Test
    public void afterTwiceRemoveElementByValueShouldCutList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        linkedList.remove(new Integer(4));
        boolean isRemoved = linkedList.remove(new Integer(5));

        assertEquals(isRemoved, true);
        assertList(linkedList, asList(3, 2, 1, 6));
    }

    @Test
    public void afterRemoveAFewNotOrderElementByValueShouldCorrectList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        linkedList.remove(new Integer(4));
        linkedList.remove(new Integer(5));
        linkedList.remove(new Integer(6));
        boolean isRemoved = linkedList.remove(new Integer(5));

        assertEquals(isRemoved, false);
        assertList(linkedList, asList(3, 2, 1));
    }

    @Test
    public void removeObjectWithWrongTypeShouldNotChangeList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        boolean isRemoved = linkedList.remove("Wrong type");

        assertFalse(isRemoved);
        assertList(linkedList, CHECK_ARRAY);
    }

    @Test
    public void afterRemoveAllObjectsShouldBeEmpty() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        CHECK_ARRAY.forEach(linkedList::remove);

        assertList(linkedList, EMPTY_LIST);
    }

    private void assertList(List<Integer> linkedList, List<Integer> checkArray) {
        LOGGER.info("List for checking: " + linkedList);
        LOGGER.info("Right list:        " + checkArray + "\n");
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
    public void testAssertList() {
        assertList(new SingleLinkedList<>(asList(1, 2)), new SingleLinkedList<>(asList(1, 2)));

        assertList(new SingleLinkedList<>(asList(2, 2)), new SingleLinkedList<>(asList(2, 2)));
    }

    @Test
    public void containsAllWithEmptyListShouldReturnTrue() {
        assertTrue(linkedListCommon.containsAll(EMPTY_LIST));
    }

    @Test(expected = NullPointerException.class)
    public void containsAllWithNullShouldThrowException() {
        assertTrue(linkedListCommon.containsAll(null));
    }

    @Test
    public void containsAllByItselfShouldReturnTrue() {
        assertTrue(linkedListCommon.containsAll(linkedListCommon));
    }

    @Test
    public void containsAllByTheSameElementsShouldReturnTrue() {
        assertTrue(linkedListCommon.containsAll(CHECK_ARRAY));
    }

    @Test
    public void containsAllWithWrongTypeShouldReturnFalse() {
        assertFalse(linkedListCommon.containsAll(asList("one", "two", "three")));
    }

    @Test
    public void containsAllWithFoundAndNotFoundElementShouldReturnFalse() {
        assertFalse(linkedListCommon.containsAll(asList(1, 99)));
    }

    @Test
    public void containsAllWithOnlyFoundElementsShouldReturnFalse() {
        assertTrue(linkedListCommon.containsAll(asList(1, 2, 3)));
    }

    @Test
    public void containsAllWithOnlyNotFoundElementsShouldReturnFalse() {
        assertFalse(linkedListCommon.containsAll(asList(99, 98, 97)));
    }

    @Test
    public void addAllWithEmptyListShouldReturnFalseAndNotChangeList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        assertFalse(linkedList.addAll(EMPTY_LIST));
        assertList(linkedList, CHECK_ARRAY);
    }

    @Test
    public void afterAddAllShouldBeCorrectList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        assertTrue(linkedList.addAll(asList(0, 9, 8, 7)));
        assertList(linkedList, asList(5, 4, 3, 2, 1, 5, 6, 0, 9, 8, 7));
    }

    @Test(expected = NullPointerException.class)
    public void addAllWrongTypeShouldThrowException() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        assertTrue(linkedList.addAll(null));
        assertList(linkedList, CHECK_ARRAY);
    }

    @Test
    public void addAllByIndexEmptyListShouldNotChangeList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        assertTrue(linkedList.addAll(0, EMPTY_LIST));
        assertList(linkedList, CHECK_ARRAY);
    }

    @Test
    public void addAllByIndexToTailListShouldChangeList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        assertTrue(linkedList.addAll(CHECK_ARRAY.size() - 1, asList(0, 9, 8, 7)));
        assertList(linkedList, asList(5, 4, 3, 2, 1, 5, 6, 0, 9, 8, 7));
    }

    @Test
    public void addAllByIndexToMiddleListShouldThrowException() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        assertTrue(linkedList.addAll(2, asList(11, 12, 13, 14)));
        assertList(linkedList, asList(5, 4, 3, 11, 12, 13, 14, 2, 1, 5, 6));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAllByIndexTooManyIndexShouldThrowException() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        linkedList.addAll(CHECK_ARRAY.size(), asList(0, 9, 8, 7));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAllByIndexTooLittleIndexShouldThrowException() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        linkedList.addAll(-1, asList(0, 9, 8, 7));
    }

    @Test
    public void removeAllWithEmptyListShouldNotChangeList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        assertFalse(linkedList.removeAll(EMPTY_LIST));
        assertList(linkedList, CHECK_ARRAY);
    }

    @Test(expected = NullPointerException.class)
    public void removeAllWithNullShouldThrowException() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        linkedList.removeAll(null);
    }

    @Test
    public void removeAllLastElementShouldCorrectChangeList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        assertTrue(linkedList.removeAll(singletonList(6)));
        assertList(linkedList, asList(5, 4, 3, 2, 1, 5));
    }

    @Test
    public void removeAllWithMultipleOccurrenceElementShouldCorrectChangeList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        assertTrue(linkedList.removeAll(singletonList(5)));
        assertList(linkedList, asList(4, 3, 2, 1, 6));
    }

    @Test
    public void removeAllWithAFewElementShouldCorrectChangeList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        assertTrue(linkedList.removeAll(asList(4, 3, 1)));
        assertList(linkedList, asList(5, 2, 5, 6));
    }

    @Test
    public void removeAllUnknownElementShouldNotChangeList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);

        assertFalse(linkedList.removeAll(asList(0, 9, 8, 7)));
        assertList(linkedList, CHECK_ARRAY);
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
    public void toStringLikeArrayList() {
        List<Integer> linkedList = new SingleLinkedList<>(CHECK_ARRAY);
        assertEquals(linkedList.toString(), CHECK_ARRAY.toString());
    }

    @Test(expected = IllegalStateException.class)
    public void listIteratorSetIllegalState() throws Exception {
        new SingleLinkedList<>(CHECK_ARRAY).listIterator().set(1);
    }

    @Test
    public void listIterator() throws Exception {
        listIterator(new SingleLinkedList<>(CHECK_ARRAY));
    }

    private void listIterator(List<Integer> linkedList) {
        LOGGER.info("List for checking: " + linkedList);
        LOGGER.info("Right list:        " + CHECK_ARRAY + "\n");

        for (int indexCheckingIterator = 0; indexCheckingIterator < linkedList.size(); ++indexCheckingIterator) {
            ListIterator<Integer> integerListIterator = linkedList.listIterator();
            ListIterator<Integer> integerListIteratorCheck = CHECK_ARRAY.listIterator();
            int currentIndexIterator = 0;
            while (integerListIterator.hasNext() && currentIndexIterator <= indexCheckingIterator) {
                ++currentIndexIterator;
                checkSettingsListIterators(integerListIterator, integerListIteratorCheck);

                Integer next = integerListIterator.next();
                LOGGER.info("Next:     " + next);
                assertEquals(next, integerListIteratorCheck.next());
            }
            LOGGER.info("-------------");

            checkSettingsListIterators(integerListIterator, integerListIteratorCheck);

            while (integerListIterator.hasPrevious()) {
                checkSettingsListIterators(integerListIterator, integerListIteratorCheck);

                Integer previous = integerListIterator.previous();
                LOGGER.info("Previous: " + previous);
                assertEquals(previous, integerListIteratorCheck.previous());
            }
            LOGGER.info("=============\n");
        }

        ListIterator<Integer> listIterator = linkedList.listIterator();

        assertTrue(listIterator.hasNext());
        assertFalse(listIterator.hasPrevious());
        assertEquals(listIterator.nextIndex(), 0);
        assertEquals(listIterator.previousIndex(), -1);

        assertTrue(listIterator.next() == 5);

        assertEquals(listIterator.nextIndex(), 1);
        assertEquals(listIterator.nextIndex(), 1);
        assertEquals(listIterator.previousIndex(), 0);
        assertEquals(listIterator.previousIndex(), 0);

        listIterator.set(7);
        assertList(linkedList, asList(7, 4, 3, 2, 1, 5, 6));

        assertTrue(listIterator.hasNext());
        assertTrue(listIterator.hasPrevious());
        assertTrue(listIterator.next() == 4);
        assertEquals(listIterator.nextIndex(), 2);

        listIterator.set(8);
        assertList(linkedList, asList(7, 8, 3, 2, 1, 5, 6));

        listIterator.remove();
        assertList(linkedList, asList(7, 3, 2, 1, 5, 6));

        assertTrue(listIterator.hasNext());
        assertTrue(listIterator.hasPrevious());
        assertEquals(listIterator.nextIndex(), 1);
        assertEquals(listIterator.previousIndex(), 0);
        assertTrue(listIterator.next() == 3);

        listIterator.remove();
        assertList(linkedList, asList(7, 2, 1, 5, 6));

        assertTrue(listIterator.hasNext());
        assertTrue(listIterator.hasPrevious());
        assertEquals(listIterator.nextIndex(), 1);
        assertEquals(listIterator.previousIndex(), 0);

        assertEquals(listIterator.previous(), new Integer(7));

        assertEquals(listIterator.nextIndex(), 0);
        assertEquals(listIterator.previousIndex(), -1);
        assertFalse(listIterator.hasPrevious());

        listIterator.set(9);
        assertList(linkedList, asList(9, 2, 1, 5, 6));

        assertTrue(listIterator.hasNext());
        assertFalse(listIterator.hasPrevious());
        assertEquals(listIterator.nextIndex(), 0);
        assertEquals(listIterator.previousIndex(), -1);

        listIterator.remove();
        assertList(linkedList, asList(2, 1, 5, 6));

        assertTrue(listIterator.hasNext());
        assertFalse(listIterator.hasPrevious());
        assertEquals(listIterator.nextIndex(), 0);
        assertEquals(listIterator.previousIndex(), -1);

        listIterator.add(10);
        assertList(linkedList, asList(10, 2, 1, 5, 6));

        assertTrue(listIterator.hasNext());
        assertTrue(listIterator.hasPrevious());
        assertEquals(listIterator.nextIndex(), 1);
        assertEquals(listIterator.previousIndex(), 0);

        assertEquals(listIterator.previous(), new Integer(10));

        assertEquals(listIterator.nextIndex(), 0);
        assertEquals(listIterator.previousIndex(), -1);
        assertFalse(listIterator.hasPrevious());
    }

    private void checkSettingsListIterators(ListIterator<Integer> integerListIterator, ListIterator<Integer> integerListIteratorCheck) {
        assertEquals("Wrong hasNext", integerListIterator.hasNext(), integerListIteratorCheck.hasNext());
        assertEquals("Wrong hasPrevious", integerListIterator.hasPrevious(), integerListIteratorCheck.hasPrevious());
        assertEquals("Wrong nextIndex", integerListIterator.nextIndex(), integerListIteratorCheck.nextIndex());
        assertEquals("Wrong previousIndex", integerListIterator.previousIndex(), integerListIteratorCheck.previousIndex());
    }
}