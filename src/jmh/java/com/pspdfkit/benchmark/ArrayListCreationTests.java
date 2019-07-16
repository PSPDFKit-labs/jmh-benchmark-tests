package com.pspdfkit.benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
/**
 * To run from command line type "java -jar benchmark-tests.jar  '.*ArrayListCreationTests.*' -wi 5 -i 5 -f 1".
 */
public class ArrayListCreationTests {

    @Param({"10", "50", "100", "500", "1000", "10000", "100000"})
    int initialSize;

    @Benchmark
    public List<String> AL_creation() {
        return new ArrayList<>(initialSize);
    }
}