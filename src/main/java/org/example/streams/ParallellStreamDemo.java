package org.example.streams;

import java.util.stream.IntStream;

import static org.example.mappers.Mappers.tregDobling;

public class ParallellStreamDemo {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        IntStream.range(1, 10).boxed()
                .parallel()
                .map(tregDobling)
                .toList();
        ;
        long stop = System.currentTimeMillis();
        System.out.println("Brukte " + (stop - start) / 1000 + " sekunder");
    }
}
