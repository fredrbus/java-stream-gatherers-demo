package org.example.collectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ListeCollector<T> implements Collector<T, List<T>, List<T>> {

    @Override
    // Funksjon som genererer et initielt akkumulatorobjekt
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    // Funksjon som legger til et element i akkumulatorobjektet
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    // Funksjon som kombinerer to akkumulatorobjekter
    //      - brukes ved samling av resultatet til parallelle streams
    public BinaryOperator<List<T>> combiner() {
        return (a, b) -> {
            a.addAll(b);
            return a;
        };
    }

    @Override
    // Funksjon som konverterer akkumualtorobjektet til det ferdige resultatobjektet
    public Function<List<T>, List<T>> finisher() {
        return List::copyOf;
    }

    @Override
    // Et sett med karakteristikker som hjelper Stream-API'et med optimaliseringer
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.UNORDERED);
    }

    static <T> ListeCollector<T> tilListe() {
        return new ListeCollector<T>();
    }
}
