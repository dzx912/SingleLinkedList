package org.sberbank;

import java.util.*;

/**
 * Created by Anton Lenok on 02.10.16.
 * Class for training implementation single linked list
 */
public class SingleLinkedList<T> implements List<T> {
    private ElementList<T> first;
    private ElementList<T> last;
    private int size;

    public SingleLinkedList() {
        clear();
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean contains(Object inputObject) {
        boolean find = indexOf(inputObject) != -1;
        return find;
    }

    public Iterator<T> iterator() {
        return new SingleLinkedIterator<>(first);
    }

    public boolean add(T value) {
        ElementList<T> newElement = new ElementList<>(value);
        this.last.setNext(newElement);
        this.last = newElement;
        this.size += 1;
        return true;
    }

    public void clear() {
        ElementList<T> initElement = new ElementList<>();
        initElement.setNext(initElement);
        this.first = initElement;
        this.last = initElement;
        this.size = 0;
    }

    public T get(int index) {
        return search(index).getValue();
    }

    public T set(int index, T element) {
        search(index).setValue(element);
        return element;
    }

    public void add(int index, T element) {
        ElementList<T> searchElement = search(index);
        ElementList<T> newElement = new ElementList<>(element);
        newElement.setNext(searchElement.getNext());
        searchElement.setNext(newElement);
    }

    public T remove(int index) {
        ElementList<T> searchElement = search(index - 1);
        ElementList<T> removedElement = searchElement.getNext();
        searchElement.setNext(removedElement.getNext());
        size -= 1;
        return removedElement.getValue();
    }

    public Object[] toArray() {
        Object[] outputArray = new Object[size()];
        Iterator<T> iterator = iterator();
        for (int indexArray = 0; iterator.hasNext(); indexArray++) {
            T element = iterator.next();
            outputArray[indexArray] = element;
        }
        return outputArray;
    }

    public int indexOf(Object inputObject) {
        //TODO: Find correct way check instanceof
        T searchObject = (T) inputObject;
        Iterator<T> iterator = iterator();
        for (int indexSearch = 0; iterator.hasNext(); indexSearch++) {
            T element = iterator.next();
            if (element.equals(searchObject)) {
                return indexSearch;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object inputObject) {
        //TODO: Find correct way check instanceof
        T searchObject = (T) inputObject;
        Iterator<T> iterator = iterator();
        int find = -1;
        for (int indexSearch = 0; iterator.hasNext(); indexSearch++) {
            T element = iterator.next();
            if (element.equals(searchObject)) {
                find = indexSearch;
            }
        }
        return find;
    }

    private ElementList<T> search(int indexSearch) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Doesn't have any elements");
        }
        ElementList<T> currentElement = this.first;
        for (int indexElement = 0; indexElement <= indexSearch; ++indexElement) {
            currentElement = currentElement.getNext();
            if (currentElement == null) {
                throw new IndexOutOfBoundsException("Too much index");
            }
        }
        return currentElement;
    }

    public <T1> T1[] toArray(T1[] inputArray) {
        if (inputArray.length < size()) {
            return (T1[]) Arrays.copyOf(toArray(), size(), inputArray.getClass());
        }
        System.arraycopy(toArray(), 0, inputArray, 0, size());
        if (inputArray.length > size) {
            inputArray[size] = null;
        }
        return inputArray;
    }

    public boolean remove(Object removedElement) {
        int indexRemovedElement;
        boolean find = false;
        while (-1 != (indexRemovedElement = indexOf(removedElement))) {
            remove(indexRemovedElement);
            find = true;
        }
        return find;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public ListIterator<T> listIterator() {
        return null;
    }

    public ListIterator<T> listIterator(int index) {
        return null;
    }

    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

}
