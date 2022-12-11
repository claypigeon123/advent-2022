package com.cp.advent2022.data.day11.processor.impl;

import com.cp.advent2022.data.day11.Monkey;
import com.cp.advent2022.data.day11.processor.RoundProcessor;

import java.util.Comparator;
import java.util.List;

public class SimpleRoundProcessor implements RoundProcessor {

    @Override
    public int processRounds(List<Monkey> monkeys, int rounds) {
        for (int i = 0; i < rounds; i++) {
            for (Monkey monkey : monkeys) {
                processSingle(monkeys, monkey);
            }
        }

        return monkeys.stream()
            .sorted(Comparator.comparingInt(Monkey::getInspectedCount).reversed())
            .mapToInt(Monkey::getInspectedCount)
            .limit(2)
            .reduce((left, right) -> left * right)
            .orElseThrow();
    }

    private void processSingle(List<Monkey> monkeys, Monkey monkey) {
        int size = monkey.getItems().size();
        for (int k = 0; k < size; k++) {
            if (monkey.getItems().peek() == null) continue;

            int worry = monkey.getItems().poll();
            worry = monkey.getOperation().apply(worry);
            worry = Math.floorDiv(worry, 3);
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
