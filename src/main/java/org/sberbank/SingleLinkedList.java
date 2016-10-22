package org.sberbank;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Anton Lenok on 02.10.16.
 */
public class SingleLinkedList<T> implements List<T> {
    private ElementList<T> first;
    private ElementList<T> last;
    private T value;
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

    public boolean contains(Object o) {
        return false;
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
        //TODO: Первый элемент
        //TODO: Последний элемент
        ElementList<T> searchElement = search(index - 1);
        ElementList<T> removedElement = searchElement.getNext();
        searchElement.setNext(removedElement.getNext());
        return removedElement.getValue();
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public int indexOf(Object o) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
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

    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    public boolean remove(Object o) {
        return false;
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
