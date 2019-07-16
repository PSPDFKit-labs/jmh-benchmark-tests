package com.pspdfkit.benchmark.datastructure;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is a modification of an {@link ArrayList} which removes existing copies of an item in the data structure
 * before adding them.
 */
public class OrderedSetArrayList<E> extends ArrayList<E> implements Cloneable, Serializable {

    public OrderedSetArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    public OrderedSetArrayList() {
        super();
    }

    @Override
    public boolean add(E object) {
        super.remove(object);
        return super.add(object);
    }
}
