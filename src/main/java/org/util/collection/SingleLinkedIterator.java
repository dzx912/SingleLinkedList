package org.util.collection;

import java.util.Iterator;

/**
 * Created by Anton Lenok on 02.10.16.
 * Class iterator for functional like forEach with SingleLinkedList
 */
public class SingleLinkedIterator<T> implements Iterator<T> {

    private ElementList<T> elementList;

    public SingleLinkedIterator(ElementList<T> elementList) {
        this.elementList = elementList;
    }

    public boolean hasNext() {
        return elementList.getNext() != null;
    }

    public T next() {
        elementList = elementList.getNext();
        return elementList.getValue();
    }
}
