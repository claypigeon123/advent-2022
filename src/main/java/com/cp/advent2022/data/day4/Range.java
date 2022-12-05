package com.cp.advent2022.data.day4;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@AllArgsConstructor
public class Range {
    private int from;
    private int to;

    public boolean coversRange(Range other) {
        return (other.from <= from && other.to >= to) || (from <= other.from && to >= other.to);
    }

    public boolean overlapsRange(Range other) {
        Set<Integer> numbersInRange = numbersInRange();
        numbersInRange.retainAll(other.numbersInRange());

        return !numbersInRange.isEmpty();
    }

    private Set<Integer> numbersInRange() {
        return IntStream.range(from, to + 1)
            .boxed()
            .collect(Collectors.toSet());
    }
}
