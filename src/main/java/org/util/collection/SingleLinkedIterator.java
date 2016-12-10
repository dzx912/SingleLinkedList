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

    private OrderIterator orderIterator;

    private SingleLinkedList<T> linkedList;

    public SingleLinkedIterator(SingleLinkedList<T> linkedList, ElementList<T> elementList, ElementList<T> last) {
        this.linkedList = linkedList;
        this.first = elementList;
        this.elementList = elementList;
        this.last = last;
        this.index = 0;

        this.orderIterator = OrderIterator.straight;
    }

    public boolean hasNext() {
        return elementList != last || orderIterator != OrderIterator.straight;
    }

    public T next() {
        this.index += 1;
        elementList = elementList.getNext();
        orderIterator = OrderIterator.straight;
        return elementList.getValue();
    }

    @Override
    public boolean hasPrevious() {
        return 0 < index;
    }

    @Override
    public T previous() {
        movePrevious();
        orderIterator = OrderIterator.revers;
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
        elementList = first;
        index = 0;
        while (elementList.getNext() != removedElement) {
            ++index;
            elementList = elementList.getNext();
        }

        elementList.setNext(removedElement.getNext());
        if (last == removedElement) {
            last = elementList;
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
