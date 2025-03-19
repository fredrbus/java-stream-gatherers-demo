package org.example.streams;

import java.util.stream.IntStream;

import static org.example.mappers.Mappers.tregDobling;

public class PullBasedDemo {

    public static void main(String[] args) {

        var doblet = IntStream.range(1, 6).boxed()
                .map(tregDobling)
                .findFirst();
        ;
        System.out.println(doblet);
    }



}
