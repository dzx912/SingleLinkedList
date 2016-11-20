package org.util.collection;

import java.util.ListIterator;

/**
 * Created by Anton Lenok on 02.10.16.
 * Class iterator for functional like forEach with SingleLinkedList
 */
public class SingleLinkedIterator<T> implements ListIterator<T> {

    private ElementList<T> first;
    private ElementList<T> elementList;
    private ElementList<T> last;
    private int index;
    private boolean hasPrevious;
    private boolean hasNext;

    private SingleLinkedList<T> linkedList;

    public SingleLinkedIterator(SingleLinkedList<T> linkedList, ElementList<T> elementList, ElementList<T> last) {
        this.linkedList = linkedList;
        this.first = elementList;
        this.elementList = elementList;
        this.last = last;
        this.index = 0;

        this.hasPrevious = false;
        this.hasNext = first != last;
    }

    public boolean hasNext() {
        return hasNext;
    }

    public T next() {
        this.index += 1;
        elementList = elementList.getNext();
        hasPrevious = true;
        hasNext = elementList.getNext() != null && elementList != last;
        return elementList.getValue();
    }

    @Override
    public boolean hasPrevious() {
        return hasPrevious;
    }

    @Override
    public T previous() {
        movePrevious();
        hasPrevious = first.getNext() != elementList;
        hasNext = true;
        return elementList.getValue();
    }

    private void movePrevious() {
        index -= 1;

        elementList = first;
        for (int indexElement = 0; indexElement <= index; ++indexElement) {
            elementList = elementList.getNext();
        }
    }

    @Override
    public int nextIndex() {
        return index;
    }

    @Override
    public int previousIndex() {
        return this.index - 1;
    }

    @Override
    public void remove() {
        ElementList<T> removedElement = elementList;
        if (first.getNext() != elementList) {
            index -= 1;
        } else {
            hasPrevious = false;
        }

        elementList = first;
        while (elementList.getNext() != removedElement) {
            elementList = elementList.getNext();
        }

        elementList.setNext(removedElement.getNext());
        if (last == removedElement) {
            last = elementList;
            hasNext = false;
        }

        linkedList.decreaseSize();
    }

    @Override
    public void set(T element) {
        if (first == elementList) {
            throw new IllegalStateException();
        }
        elementList.setValue(element);
    }

    @Override
    public void add(T element) {
        ElementList<T> nextElement = elementList.getNext();
        ElementList<T> newElement = new ElementList<>(element);
        elementList.setNext(newElement);
        newElement.setNext(nextElement);
        elementList = newElement;
        index += 1;
        linkedList.increaseSize();
        hasPrevious = true;
        hasNext = elementList.getNext() != null && elementList != last;
    }

    @Override
    public String toString() {
        return "SingleLinkedIterator{" +
                "index=" + index +
                ", elementList=" + elementList +
                ", linkedList=" + linkedList +
                ", first=" + first +
                ", last=" + last +
                '}';
    }
}
