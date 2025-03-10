package org.example.collectors;

import java.util.stream.IntStream;

public class CollectorsDemo {

    public static void main(String[] args) {
        var mineTall = IntStream.range(0, 1000).boxed()
                .map((tall) -> {
                    System.out.println("Dobler " + tall);
                    return tall * 2;
                })
                .findFirst();
    }

}
