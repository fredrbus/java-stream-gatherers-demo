package org.example.gatherers;

import java.util.stream.Gatherers;
import java.util.stream.IntStream;

import static org.example.gatherers.ListeGatherer.tilListe;

public class InnebygdeGatherers {

    public static void main(String[] args) {

        var gathered = IntStream.range(0, 10).boxed()
                .gather(Gatherers.windowSliding(3))
                .toList();

        System.out.println(gathered);
    }



}
