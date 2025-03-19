package org.example.gatherers;

import java.util.function.Function;
import java.util.stream.Gatherer;

public abstract class StatelessGatherer {

    public static void main(String[] args) {

    }

    public static <T> Gatherer<T, ?, T> doNothing() {
        Gatherer.Integrator<Void, T, T> integrator = (_, element, downstream) -> downstream.push(element);
        return Gatherer.of(integrator);
    }

    public static Gatherer<Integer, ?, Integer> square() {
        Gatherer.Integrator<Void, Integer, Integer> integrator = (_, number, downstream) -> downstream.push(number * number);

        return Gatherer.of(integrator);
    }

    public static <T,R> Gatherer<T, ?, R> map(Function<T, R> mapper){
        Gatherer.Integrator<Void, T, R> integrator = (_, element, downstream) ->  downstream.push(mapper.apply(element));
        return Gatherer.of(integrator);
    }
}
