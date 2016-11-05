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

    public SingleLinkedIterator(ElementList<T> elementList, ElementList<T> last) {
        this.first = elementList;
        this.elementList = elementList;
        this.last = last;
        this.index = 0;
    }

    public boolean hasNext() {
        return elementList.getNext() != null && elementList != last;
    }

    public T next() {
        this.index += 1;
        elementList = elementList.getNext();
        return elementList.getValue();
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public T previous() {
        movePrevious();
        return elementList.getValue();
    }

    private void movePrevious() {
        this.index -= 1;
        elementList = first;
        for (int indexElement = 0; indexElement < index; ++indexElement) {
            elementList = elementList.getNext();
        }
    }

    @Override
    public int nextIndex() {
        return this.index + 1;
    }

    @Override
    public int previousIndex() {
        return this.index - 1;
    }

    @Override
    public void remove() {
        ElementList<T> removedElement = this.elementList;
        if (first != this.elementList) {
            movePrevious();
            this.elementList.setNext(removedElement.getNext());
        }
        if (last == removedElement) {
            last = elementList;
        }
    }

    @Override
    public void set(T element) {
        this.elementList.setValue(element);
    }

    @Override
    public void add(T element) {
        ElementList<T> nextElement = this.elementList.getNext();
        ElementList<T> newElement = new ElementList<>(element);
        this.elementList.setNext(nextElement);
        newElement.setNext(nextElement);
    }
}
