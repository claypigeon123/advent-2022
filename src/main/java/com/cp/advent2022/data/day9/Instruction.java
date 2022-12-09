package com.cp.advent2022.data.day9;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Instruction {
    private Direction direction;
    private int n;

    public static Instruction fromString(String str) {
        String[] split = str.split(" ");
        return new Instruction(Direction.valueOf(split[0]), Integer.parseInt(split[1]));
    }
}
