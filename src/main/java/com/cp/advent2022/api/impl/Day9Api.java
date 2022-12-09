package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.component.AdventResourceLoader;
import com.cp.advent2022.data.day9.GridItem;
import com.cp.advent2022.data.day9.Instruction;
import com.cp.advent2022.data.day9.processor.GridKnotInstructionProcessor;
import com.cp.advent2022.data.day9.processor.impl.SimpleGridKnotInstructionProcessor;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Command(name = "day9", mixinStandardHelpOptions = true)
public class Day9Api extends DayApi {

    private final GridKnotInstructionProcessor simpleProcessor;

    public Day9Api(AdventResourceLoader adventResourceLoader) {
        super(9, adventResourceLoader);
        this.simpleProcessor = new SimpleGridKnotInstructionProcessor();
    }

    @Override
    protected void execute() {
        List<Instruction> instructions = new ArrayList<>();

        for (String line : lines) {
            instructions.add(Instruction.fromString(line));
        }

        GridItem head = new GridItem();
        GridItem tail = new GridItem();
        Set<String> visited = new HashSet<>();
        visited.add(tail.positionToString());

        for (Instruction instruction : instructions) {
            visited.addAll(simpleProcessor.process(head, tail, instruction));
        }

        // --

        System.out.printf("1. The tail visited %d unique position(s) on the grid.\n\n", visited.size());
    }
}
