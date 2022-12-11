package com.cp.advent2022.data.day11.processor.impl;

import com.cp.advent2022.data.day11.Monkey;
import com.cp.advent2022.data.day11.processor.MonkeyMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
        Queue<Integer> startingItems = new LinkedList<>();
        for (String item : token.split(", ")) {
            startingItems.offer(Integer.parseInt(item));
        }

        // Operation
        token = lines.get(2);
        token = token.substring(token.indexOf("old") + 4);
        String[] operationTokens = token.split(" ");
        Function<Integer, Integer> operation = old -> {
            String target = operationTokens[1];
            int targetInt = target.equals("old") ? old : Integer.parseInt(target);

            return switch (operationTokens[0]) {
                case "*" -> old * targetInt;
                case "+" -> old + targetInt;
                default -> old;
            };
        };

        // Test
        token = lines.get(3);
        token = token.substring(token.lastIndexOf("by") + 3);
        int divisibleBy = Integer.parseInt(token);

        token = lines.get(4);
        token = token.substring(token.lastIndexOf("monkey") + 7);
        int trueThrowTo = Integer.parseInt(token);

        token = lines.get(5);
        token = token.substring(token.lastIndexOf("monkey") + 7);
        int falseThrowTo = Integer.parseInt(token);

        Function<Integer, Integer> test = old -> {
            if (old % divisibleBy == 0) {
                return trueThrowTo;
            }

            return falseThrowTo;
        };

        return new Monkey(index, 0, startingItems, operation, test);
    }
}
