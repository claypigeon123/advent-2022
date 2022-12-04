package com.cp.advent2022.data.day2;

import java.util.List;

public enum Hand {
    ROCK(List.of("A", "X")),
    PAPER(List.of("B", "Y")),
    SCISSORS(List.of("C", "Z"));

    private final List<String> literals;

    Hand(List<String> literals) {
        this.literals = literals;
    }

    public static Hand fromLiteral(String literal) {
        for (Hand hand : Hand.values()) {
            if (hand.literals.contains(literal)) {
                return hand;
            }
        }

        throw new IllegalArgumentException();
    }
}
