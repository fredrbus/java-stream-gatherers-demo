package org.example.gatherers;

import java.util.stream.Gatherers;
import java.util.stream.IntStream;

import static org.example.mappers.Mappers.tregDobling;

public class MapConcurrentDemo {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        IntStream.range(1, 500).boxed()
                .gather(Gatherers.mapConcurrent(100, tregDobling))
                .toList();
        ;
        long stop = System.currentTimeMillis();
        System.out.println("Brukte " + (stop - start) / 1000 + " sekunder");
    }
}
