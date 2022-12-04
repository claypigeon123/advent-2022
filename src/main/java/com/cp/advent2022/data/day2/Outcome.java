package com.cp.advent2022.data.day2;

import java.util.List;

public enum Outcome {
    LOST(List.of("X")),
    DRAW(List.of("Y")),
    WON(List.of("Z"));

    private final List<String> literals;

    Outcome(List<String> literals) {
        this.literals = literals;
    }

    public static Outcome fromLiteral(String literal) {
        for (Outcome hand : Outcome.values()) {
            if (hand.literals.contains(literal)) {
                return hand;
            }
        }

        throw new IllegalArgumentException();
    }
}
