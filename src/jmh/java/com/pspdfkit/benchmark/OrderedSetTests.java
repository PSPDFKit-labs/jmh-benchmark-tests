package com.pspdfkit.benchmark;

import com.pspdfkit.benchmark.datastructure.OrderedSetArrayList;
import com.pspdfkit.benchmark.datastructure.OrderedSetLinkedList;
import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
/**
 * To run from command line type "java -jar benchmark-tests.jar  '.*OrderedSetTests.*' -wi 5 -i 5 -f 1".
 */
public class OrderedSetTests {

    @Param({"500"})
    int SIZE;
    int mid;
    int end;
    String element;

    OrderedSetArrayList<String> orderedSetArrayList;
    OrderedSetLinkedList<String> orderedSetLinkedList;

    @Setup
    public void load() {
        element = "Dawson College";
        mid = SIZE / 2;
        end = SIZE - 1;

        orderedSetArrayList = new OrderedSetArrayList<>(500);
        orderedSetLinkedList = new OrderedSetLinkedList<>();

        // Load data structures
        for (int x = 0; x < end; ++x) {
            orderedSetArrayList.add("");
            orderedSetLinkedList.add("");
        }
    }

    // OrderedSetArrayList

    @Benchmark
    public boolean OrderedSetArrayListAddLast() {
        return orderedSetArrayList.add("Dawson College");
    }

    @Benchmark
    public boolean OrderedSetArrayListEditLast() {
        orderedSetArrayList.add(element);
        return orderedSetArrayList.add(element);
    }

    // OrderedSetLinkedList

    @Benchmark
    public boolean OrderedSetLinkedListAddLast() {
        return orderedSetLinkedList.add("Dawson College");
    }

    @Benchmark
    public boolean OrderedSetLinkedListEditLast() {
        orderedSetLinkedList.add(element);
        return orderedSetLinkedList.add(element);
    }
}