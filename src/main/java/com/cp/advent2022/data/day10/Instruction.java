package com.cp.advent2022.data.day10;

import lombok.Getter;

public enum Instruction {
    NO_OPERATION("noop"),
    ADD_X("addx");

    @Getter
    private final String literal;

    Instruction(String literal) {
        this.literal = literal;
    }

    public static Instruction fromLiteral(String literal) {
        for (Instruction ins : Instruction.values()) {
            if (ins.literal.equals(literal)) {
                return ins;
            }
        }

        throw new IllegalArgumentException();
    }
}
