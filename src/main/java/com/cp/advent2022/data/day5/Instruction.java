package com.cp.advent2022.data.day5;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Instruction {
    private int nToMove;
    private int from;
    private int to;

    public static Instruction fromInstructionLine(String line) {
        String cleaned = line.replaceAll("move|from|to", " ");

        List<Integer> tokens = Arrays.stream(cleaned.split(" "))
            .filter(s -> !s.isBlank())
            .map(Integer::parseInt)
            .toList();

        return new Instruction(tokens.get(0), tokens.get(1), tokens.get(2));
    }
}
