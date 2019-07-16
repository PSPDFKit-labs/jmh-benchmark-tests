package com.pspdfkit.benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
/**
 * To run from command line type "java -jar benchmark-tests.jar  '.*SortTests.*' -wi 5 -i 5 -f 1".
 */
public class SortTests {

    @Param({"10", "100", "1000"})
    int initialSize;

    LinkedList<Integer> linkedList;
    ArrayList<Integer> arrayList;

    @Setup
    public void load() {
        linkedList = new LinkedList<>();
        arrayList = new ArrayList<>(initialSize);

        // Populate the two data structures.
        for (int i = 0; i < initialSize; i++) {
            int randomValue = (int) (Math.random() * 1000);
            linkedList.add(randomValue);
            arrayList.add(randomValue);
        }
    }

    @Benchmark
    public List<Integer> LL_sortingInPlace() {
        linkedList.sort(Integer::compareTo);
        return linkedList;
    }

    @Benchmark
    public List<Integer> AL_sortingInPlace() {
        arrayList.sort(Integer::compareTo);
        return arrayList;
    }

    @Benchmark
    public Object[] LL_sortingNewArray() {
        Object[] array = linkedList.toArray();
        Arrays.sort(array);
        return array;
    }

    @Benchmark
    public Object[] AL_sortingNewArray() {
        Object[] array = arrayList.toArray();
        Arrays.sort(array);
        return array;
    }
}