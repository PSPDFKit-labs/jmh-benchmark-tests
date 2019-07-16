package com.pspdfkit.benchmark.datastructure;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * This is a modification of an {@link LinkedList} which removes existing copies of an item in the data structure
 * before adding them.
 */
public class OrderedSetLinkedList<E> extends LinkedList<E> implements Cloneable, Serializable {

    public OrderedSetLinkedList() {
        super();
    }

    @Override
    public boolean add(E object) {
        super.remove(object);
        return super.add(object);
    }
}
