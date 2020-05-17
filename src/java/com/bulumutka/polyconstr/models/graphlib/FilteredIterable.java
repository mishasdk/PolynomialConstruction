package com.bulumutka.polyconstr.models.graphlib;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

class FilteredIterable<Value> implements Iterable<Value> {
    private final List<Value> list;
    private final int maxSize;
    private final Predicate<Value> predicate;
    private int size = -1;

    public FilteredIterable(List<Value> list, Predicate<Value> predicate) {
        this.list = list;
        this.maxSize = list.size();
        this.predicate = predicate;
    }

    public int size() {
        if (size != -1) {
            return size;
        }
        size = 0;
        for (var i : list) {
            if (predicate.test(i)) {
                ++size;
            }
        }
        return size;
    }

    @Override
    public Iterator<Value> iterator() {
        return new Iterator<Value>() {
            private int current = -1;

            {
                findNextCorrect();
            }

            @Override
            public boolean hasNext() {
                return current != maxSize;
            }

            @Override
            public Value next() {
                Value v = list.get(current);
                findNextCorrect();
                return v;
            }

            private void findNextCorrect() {
                ++current;
                while (current < maxSize && !predicate.test(list.get(current))) {
                    ++current;
                }
            }
        };
    }
}
