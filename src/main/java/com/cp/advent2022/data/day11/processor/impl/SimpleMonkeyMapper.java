package com.cp.advent2022.data.day11.processor.impl;

import com.cp.advent2022.data.day11.Monkey;
import com.cp.advent2022.data.day11.processor.MonkeyMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SimpleMonkeyMapper implements MonkeyMapper {

    @Override
    public Monkey createMonkey(List<String> lines) {
        // Monkey name
        String token = lines.get(0);
        token = token.substring(0, token.length() - 1).split(" ")[1];
        int index = Integer.parseInt(token);

        // Starting items
        token = lines.get(1);
        token = token.substring(token.lastIndexOf(":") + 2);
        Queue<Long> startingItems = new LinkedList<>();
        for (String item : token.split(", ")) {
            startingItems.offer(Long.parseLong(item));
        }

        // Operation
        token = lines.get(2);
        token = token.substring(token.indexOf("old") + 4);
        String[] operationTokens = token.split(" ");
        BiFunction<Long, Boolean, Long> operation = (old, useLcm) -> {
            String target = operationTokens[1];
            long targetLong = target.equals("old") ? old : Long.parseLong(target);

            return switch (operationTokens[0]) {
                case "*" -> old * targetLong;
                case "+" -> old + targetLong;
                default -> old;
            };
        };

        // Test
        token = lines.get(3);
        token = token.substring(token.lastIndexOf("by") + 3);
        long divisibleBy = Long.parseLong(token);

        token = lines.get(4);
        token = token.substring(token.lastIndexOf("monkey") + 7);
        int trueThrowTo = Integer.parseInt(token);

        token = lines.get(5);
        token = token.substring(token.lastIndexOf("monkey") + 7);
        int falseThrowTo = Integer.parseInt(token);

        Function<Long, Integer> test = old -> {
            if (old % divisibleBy == 0) {
                return trueThrowTo;
            }

            return falseThrowTo;
        };

        return new Monkey(index, 0, startingItems, operation, divisibleBy, test);
    }
}
