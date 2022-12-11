package com.cp.advent2022.data.day11;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Queue;
import java.util.function.BiFunction;
import java.util.function.Function;

@Data
@AllArgsConstructor
public class Monkey {
    private final int index;

    private int inspectedCount;

    private Queue<Long> items;

    private BiFunction<Long, Boolean, Long> operation;

    private long divisibleBy;

    private Function<Long, Integer> test;
}
