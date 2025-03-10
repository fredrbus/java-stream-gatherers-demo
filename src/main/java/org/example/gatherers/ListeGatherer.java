package org.example.gatherers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

public class ListeGatherer<T> implements Gatherer<T, List<T>, List<T>> {

    @Override
    // Initiell state
    //      - tilsvarende Collector.supplier()
    public Supplier<List<T>> initializer() {
        return ArrayList::new;
    }

    @Override
    // Aggregatfunksjon
    //      - tilsvarende Collector.accumulator()
    public Integrator<List<T>, T, List<T>> integrator() {
        return (a, b,  _) -> a.add(b);
    }

    @Override
    // Akkurat det samme som Collector.combiner()
    public BinaryOperator<List<T>> combiner() {
        return (a, b) -> {
            a.addAll(b);
            return a;
        };
    }

    @Override
    // Tilsvarer Collector.finisher()
    //      - men dette er ikke en terminal function, så i stedet for å returnere en liste,
    //      dytter vi den videre ned strømmen!
    public BiConsumer<List<T>, Downstream<? super List<T>>> finisher() {
        return (liste, downstream) -> downstream.push(liste);
    }

    @Override
    // Lar oss sende output av denne gathereren til en annen
    public <RR> Gatherer<T, ?, RR> andThen(Gatherer<? super List<T>, ?, ? extends RR> that) {
        return Gatherer.super.andThen(that);
    }

    static <T> ListeGatherer<T> tilListe() {
        return new ListeGatherer<T>();
    }

}
