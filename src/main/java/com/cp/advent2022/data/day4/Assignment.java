package com.cp.advent2022.data.day4;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Assignment {

    private Range firstTask;

    private Range secondTask;

    public static Assignment fromString(String str) {
        String[] tokens = str.split(",");

        String[] firstTaskTokens = tokens[0].split("-");
        Range firstTask = new Range(Integer.parseInt(firstTaskTokens[0]), Integer.parseInt(firstTaskTokens[1]));

        String[] secondTaskTokens = tokens[1].split("-");
        Range secondTask = new Range(Integer.parseInt(secondTaskTokens[0]), Integer.parseInt(secondTaskTokens[1]));

        return new Assignment(firstTask, secondTask);
    }
}
