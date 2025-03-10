package org.example.mappers;

import java.util.function.Function;

public abstract class Mappers {

    public static Function<Integer, Integer> tregDobling = (x) -> {
        try {
            System.out.println("Dobler " + x + "...");
            Thread.sleep(1000);
            return x * 2;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    };

}
