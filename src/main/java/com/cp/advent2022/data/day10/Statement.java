package com.cp.advent2022.data.day10;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Statement {
    private Instruction instruction;
    private List<String> arguments;

    public static Statement fromString(String line) {
        String[] split = line.split(" ");
        Instruction instruction = Instruction.fromLiteral(split[0]);

        return new Statement(instruction, Arrays.stream(split).skip(1).toList());
    }
}
