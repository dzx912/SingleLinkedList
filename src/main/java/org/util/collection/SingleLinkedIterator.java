package org.util.collection;

import java.util.Iterator;

/**
 * Created by Anton Lenok on 02.10.16.
 * Class iterator for functional like forEach with SingleLinkedList
 */
public class SingleLinkedIterator<T> implements Iterator<T> {

    private ElementList<T> elementList;
    private ElementList<T> last;

    public SingleLinkedIterator(ElementList<T> elementList, ElementList<T> last) {
        this.elementList = elementList;
        this.last = last;
    }

    public boolean hasNext() {
        return elementList.getNext() != null && elementList != last;
    }

    public T next() {
        elementList = elementList.getNext();
        return elementList.getValue();
    }
}
