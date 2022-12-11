package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.aspect.resloader.LoadAdventResource;
import com.cp.advent2022.data.day9.GridItem;
import com.cp.advent2022.data.day9.Instruction;
import com.cp.advent2022.data.day9.processor.GridKnotInstructionProcessor;
import com.cp.advent2022.data.day9.processor.impl.GridKnotInstructionProcessorImpl;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Component
@LoadAdventResource(9)
@Command(name = "day9", mixinStandardHelpOptions = true)
public class Day9Api extends DayApi {

    private final GridKnotInstructionProcessor instructionProcessor;

    public Day9Api() {
        this.instructionProcessor = new GridKnotInstructionProcessorImpl();
    }

    @Override
    protected void execute() {
        List<Instruction> instructions = new ArrayList<>();

        for (String line : lines) {
            instructions.add(Instruction.fromString(line));
        }

        List<GridItem> gridItemsPartOne = IntStream.range(0, 2)
            .mapToObj(i -> new GridItem())
            .toList();
        Set<String> visitedPartOne = new HashSet<>();

        List<GridItem> gridItemsPartTwo = IntStream.range(0, 10)
            .mapToObj(i -> new GridItem())
            .toList();
        Set<String> visitedPartTwo = new HashSet<>();

        for (Instruction instruction : instructions) {
            visitedPartOne.addAll(instructionProcessor.process(gridItemsPartOne, instruction));
            visitedPartTwo.addAll(instructionProcessor.process(gridItemsPartTwo, instruction));
        }

        // --

        System.out.printf("1. The tail of the rope of 2 knots visited %d unique position(s) on the grid.\n\n", visitedPartOne.size());

        System.out.printf("2. The tail of the rope of 10 knots visited %d unique position(s) on the grid.\n\n", visitedPartTwo.size());
    }
}
