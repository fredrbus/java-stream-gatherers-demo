package org.example.streams;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.example.gatherers.ListeGatherer.tilListe;

public class StreamsIntro {
    public static void main(String[] args) {

        var partallsrøtter = Stream
                // En kilde
                .of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

                // X antall 'intermediate functions', f.eks.
                //      Mappings
                //      Filtreringer
                //      Sorteringer
                //
                .map(kvadrat)
                .filter(erPartall)
                .gather(tilListe())

                // Avsluttes med en 'terminal function', f.eks.
                //      - Aggregering (count, anyMatch, allMatch, reduce, etc.)
                //      - Samling/collect (toList, toArray, iterator, spliterator, collect)
                //      - Side-effect (forEach, forEachOrdered)
                .toList()
        ;

        System.out.println(partallsrøtter);
    }


    static Function<Integer, Integer> kvadrat = (Integer rot)  -> rot * rot;
    static Predicate<Integer> erPartall = (Integer kandidat) -> kandidat % 2 == 0;
}