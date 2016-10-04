package org.sberbank;

import java.util.Iterator;

/**
 * Created by Anton Lenok on 02.10.16.
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
