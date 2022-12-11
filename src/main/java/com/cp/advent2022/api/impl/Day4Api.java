package com.cp.advent2022.api.impl;

import com.cp.advent2022.annotation.LoadAdventResource;
import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.data.day4.Assignment;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@LoadAdventResource
@Command(name = "day4", mixinStandardHelpOptions = true)
public class Day4Api extends DayApi {

    public Day4Api() {
        super(4);
    }

    @Override
    protected void execute() {
        int coveredCounter = 0;
        int overlappedCounter = 0;

        for (String line : lines) {
            Assignment assignment = Assignment.fromString(line);

            if (assignment.getFirstTask().coversRange(assignment.getSecondTask())) {
                coveredCounter++;
            }

            if (assignment.getFirstTask().overlapsRange(assignment.getSecondTask())) {
                overlappedCounter++;
            }
        }

        // ---

        System.out.printf("1. The number of assignments that fully cover themselves is %d.\n\n", coveredCounter);

        System.out.printf("2. The number of assignments that overlap themselves is %d.\n\n", overlappedCounter);
    }
}
