package com.ks12.better_deep_dark.util;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class Triplet<A, B, C> {
    public final A first;
    public final B second;
    public final C third;

    public Triplet(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public C getThird() {
        return third;
    }

    public Object get(int index) {
        return switch (index) {
            case 0 -> first;
            case 1 -> second;
            case 2 -> third;
            default -> throw new IndexOutOfBoundsException(index);
        };
    }

    public <T> T get (int index, Class<T> type) {
        Object v = get(index);
        return type.cast(v);
    }
}
