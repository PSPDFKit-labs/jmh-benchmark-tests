package com.pspdfkit.benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Performs a set of tests to determine the Big-O performance of an array list, array deque,
 * and linked list.
 *
 * @author neon
 * @version 3.0
 *
 * Converted to JMH by smarks 2015-12-18.
 * For information on JMH, see http://openjdk.java.net/projects/code-tools/jmh/
 * After building with JMH, invoke using a command line similar to this:
 *     java -jar dist/benchmarks.jar '.*SequenceTests.*' -wi 5 -i 5 -f 1
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class SequenceTests {

    private String[] dataArray = null;

    @Param({"10000"})
    int SIZE;
    int mid;
    int end;

    List<String> arrayList;
    List<String> linkedList;
    Deque<String> arrayDeque;

    @Setup
    public void load() {
        mid = SIZE / 2;
        end = SIZE - 1;

        // Load Array
        dataArray = new String[SIZE];
        for (int x = 0; x < SIZE; ++x) {
            dataArray[x] = "";
        }

        arrayList = new ArrayList<>(Arrays.asList(dataArray));
        linkedList = new LinkedList<>(Arrays.asList(dataArray));
        arrayDeque = new ArrayDeque<>(Arrays.asList(dataArray));
    }

    // ArrayList

    @Benchmark
    public String AL_accessFirst() {
        return arrayList.get(0);
    }

    @Benchmark
    public String AL_accessMiddle() {
        return arrayList.get(mid);
    }

    @Benchmark
    public String AL_accessLast() {
        return arrayList.get(end);
    }

    @Benchmark
    public String AL_editFirst() {
        arrayList.add(0, "Dawson College");
        return arrayList.remove(0);
    }

    @Benchmark
    public String AL_editMiddle() {
        arrayList.add(mid, "Dawson College");
        return arrayList.remove(mid);
    }

    @Benchmark
    public String AL_editLast() {
        arrayList.add(end, "Dawson College");
        return arrayList.remove(end);
    }

    // LinkedList

    @Benchmark
    public String LL_accessFirst() {
        return linkedList.get(0);
    }

    @Benchmark
    public String LL_accessMiddle() {
        return linkedList.get(mid);
    }

    @Benchmark
    public String LL_accessLast() {
        return linkedList.get(end);
    }

    @Benchmark
    public String LL_editFirst() {
        linkedList.add(0, "Dawson College");
        return linkedList.remove(0);
    }

    @Benchmark
    public String LL_editMiddleIndx() {
        linkedList.add(mid, "Dawson College");
        return linkedList.remove(mid);
    }

    @Benchmark
    public String LL_editMiddleIter() {
        ListIterator<String> iter = linkedList.listIterator(mid);
        iter.add("Dawson College");
        String r = iter.previous();
        iter.remove();
        return r;
    }

    @Benchmark
    public String LL_editLast() {
        linkedList.add(end, "Dawson College");
        return linkedList.remove(end);
    }

    // ArrayDeque

    @Benchmark
    public String AD_accessFirst() {
        return arrayDeque.getFirst();
    }

    @Benchmark
    public String AD_accessLast() {
        return arrayDeque.getLast();
    }

    @Benchmark
    public String AD_editFirst() {
        arrayDeque.addFirst("Dawson College");
        return arrayDeque.removeFirst();
    }

    @Benchmark
    public String AD_editLast() {
        arrayDeque.addLast("Dawson College");
        return arrayDeque.removeLast();
    }
}