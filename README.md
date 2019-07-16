# jmh-benchmark-tests
Benchmark project using JMH (http://openjdk.java.net/projects/code-tools/jmh/) that evaluates the performance of LinkedList vs ArrayList.

# Setup the project
An interesting starting point can be found in the Github article [Ken Fogel's collections benchmarks rewritten in JMH](https://gist.github.com/stuart-marks/501d4f416679bb33ee2d).
To setup correctly the project and know more about JMH check [this introduction about JMH](http://openjdk.java.net/projects/code-tools/jmh/).
JMH is Maven based but hopefully a good samaritan decided to implement a [Gradle plugin](https://github.com/guozheng/jmh-tutorial/blob/master/README.md).

## JMH Options:
The suggested options to run the benchmark tests are `-wi 5 -i 5 -f 1`
In order to translate the options into Gradle options just type
```groovy
jmh {
    //Options
}
```

### Warmup iterations
```
-wi <int>                   Number of warmup iterations to do. Warmup iterations
                            are not counted towards the benchmark score.
```
Suggested `5` default `20`
Gradle option: `warmupIterations = 5`

### Measurement Iterations
```
-i <int>                    Number of measurement iterations to do. Measurement
                            iterations are counted towards the benchmark score.
```
Suggested `5` default `20`
Gradle option: `iterations = 5`

### Forks
```
-f <int>                    How many times to fork a single benchmark. Use 0 to
                            disable forking altogether. Warning: disabling
                            forking may have detrimental impact on benchmark
                            and infrastructure reliability, you might want
                            to use different warmup mode instead.
```
Suggested `1` default `10`
Gradle options: `fork = 1`

## Run project 
To run the project type `./gradlew clean jhm`.  
The actual results are the following
```
Benchmark                        (SIZE)  Mode  Cnt      Score     Error  Units
SequenceTests.AD_accessFirst      10000  avgt    5      2.761 ±   0.162  ns/op
SequenceTests.AD_accessLast       10000  avgt    5      3.324 ±   0.033  ns/op
SequenceTests.AD_editFirst        10000  avgt    5      4.556 ±   0.037  ns/op
SequenceTests.AD_editLast         10000  avgt    5      5.422 ±   0.062  ns/op
SequenceTests.AL_accessFirst      10000  avgt    5      2.626 ±   0.029  ns/op
SequenceTests.AL_accessLast       10000  avgt    5      2.802 ±   0.028  ns/op
SequenceTests.AL_accessMiddle     10000  avgt    5      2.808 ±   0.027  ns/op
SequenceTests.AL_editFirst        10000  avgt    5    929.768 ±   7.320  ns/op
SequenceTests.AL_editLast         10000  avgt    5     13.974 ±   0.125  ns/op
SequenceTests.AL_editMiddle       10000  avgt    5    429.727 ±   3.960  ns/op
SequenceTests.LL_accessFirst      10000  avgt    5      2.558 ±   0.013  ns/op
SequenceTests.LL_accessLast       10000  avgt    5      2.887 ±   0.021  ns/op
SequenceTests.LL_accessMiddle     10000  avgt    5   6267.620 ±  85.089  ns/op
SequenceTests.LL_editFirst        10000  avgt    5      7.545 ±   0.113  ns/op
SequenceTests.LL_editLast         10000  avgt    5     11.725 ±   0.068  ns/op
SequenceTests.LL_editMiddleIndx   10000  avgt    5  13235.446 ± 210.193  ns/op
SequenceTests.LL_editMiddleIter   10000  avgt    5   6544.322 ± 491.544  ns/op
```

What emerges is that accessing the middle of an `ArrayList` is faster than a `LinkedList` of an order of `2000x` and editing an element in the middle is faster on an `ArrayList` of an order of `15x`.

## Running selective benchmark tests
To run only a specific class build the project first by typing `./gradlew clean benchmarkJar`, then go to the build folder and run the jmh specifying the desired class (e.g. `java -jar benchmark-tests-1.0-SNAPSHOT-benchmark.jar  '.*SortTests.*' -wi 5 -i 5 -f 1`).
