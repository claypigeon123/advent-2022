package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.component.AdventResourceLoader;
import com.cp.advent2022.data.day4.Assignment;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(
    name = "day4",
    mixinStandardHelpOptions = true
)
public class Day4Api extends DayApi {

    public Day4Api(AdventResourceLoader adventResourceLoader) {
        super(4, adventResourceLoader);
    }

    @Override
    protected void execute() {
        int coveredCounter = 0;

        for (String line : lines) {
            Assignment assignment = Assignment.fromString(line);

            if (assignment.getFirstTask().coversRange(assignment.getSecondTask())) {
                coveredCounter++;
            }
        }

        System.out.printf("1. Assignments that fully cover themselves: %d\n\n", coveredCounter);
    }
}
