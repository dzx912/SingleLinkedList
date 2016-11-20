package org.util.collection;

/**
 * Created by Anton Lenok on 02.10.16.
 * Template single linked list's element
 */
public class ElementList<T> {
    private ElementList<T> next;
    private T value;

    public ElementList() {
        this.next = null;
    }

    public ElementList(T value) {
        this();
        this.value = value;
    }

    public ElementList<T> getNext() {
        return next;
    }

    public void setNext(ElementList<T> next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" + value + '}';
    }
}
