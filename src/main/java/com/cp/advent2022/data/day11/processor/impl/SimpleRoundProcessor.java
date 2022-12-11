package com.cp.advent2022.data.day11.processor.impl;

import com.cp.advent2022.data.common.PuzzleUtils;
import com.cp.advent2022.data.day11.Monkey;
import com.cp.advent2022.data.day11.processor.RoundProcessor;

import java.util.Comparator;
import java.util.List;

public class SimpleRoundProcessor implements RoundProcessor {

    @Override
    public long processRounds(List<Monkey> monkeys, int rounds, Integer quellBy) {
        Long lcm = monkeys.stream()
            .map(Monkey::getDivisibleBy)
            .reduce(1L, PuzzleUtils::leastCommonMultiple);

        for (int i = 0; i < rounds; i++) {
            for (Monkey monkey : monkeys) {
                processSingle(monkeys, monkey, quellBy, lcm);
            }
        }

        return monkeys.stream()
            .sorted(Comparator.comparingInt(Monkey::getInspectedCount).reversed())
            .mapToLong(Monkey::getInspectedCount)
            .limit(2)
            .reduce((left, right) -> left * right)
            .orElseThrow();
    }

    private void processSingle(List<Monkey> monkeys, Monkey monkey, Integer quellBy, Long lcm) {
        int size = monkey.getItems().size();

        for (int k = 0; k < size; k++) {
            if (monkey.getItems().peek() == null) continue;

            long worry = monkey.getItems().poll();
            if (quellBy != null) {
                worry = monkey.getOperation().apply(worry, false);
                worry = Math.floorDiv(worry, quellBy);
            } else {
                worry = monkey.getOperation().apply(worry, true);
                worry = worry % lcm;
            }

            monkey.setInspectedCount(monkey.getInspectedCount() + 1);

            int throwTo = monkey.getTest().apply(worry);
            monkeys.stream()
                .filter(m -> m.getIndex() == throwTo)
                .findFirst()
                .orElseThrow()
                .getItems()
                .offer(worry);
        }
    }
}
