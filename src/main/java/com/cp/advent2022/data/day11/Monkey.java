package com.cp.advent2022.data.day11;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Queue;
import java.util.function.Function;

@Data
@AllArgsConstructor
public class Monkey {
    private final int index;

    private int inspectedCount;

    private Queue<Integer> items;

    private Function<Integer, Integer> operation;

    private Function<Integer, Integer> test;

    /*public void inspectItem(int index) {
        int worry = items.poll(index);
        worry = operation.apply(worry);
        worry = Math.floorDiv(worry, 3);
        items.set(index, worry);
    }

    public void decideThrow(int index) {

    }*/
}
