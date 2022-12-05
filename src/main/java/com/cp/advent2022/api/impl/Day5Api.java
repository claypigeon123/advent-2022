package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.component.AdventResourceLoader;
import com.cp.advent2022.data.day5.CrateStorage;
import com.cp.advent2022.data.day5.Instruction;
import com.cp.advent2022.data.day5.processor.CrateStorageOperationProcessor;
import com.cp.advent2022.data.day5.processor.impl.SimpleCrateStorageOperationProcessor;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.ArrayList;
import java.util.List;

@Component
@Command(name = "day5", mixinStandardHelpOptions = true)
public class Day5Api extends DayApi {

    private final CrateStorageOperationProcessor simpleProcessor;

    public Day5Api(AdventResourceLoader adventResourceLoader) {
        super(5, adventResourceLoader);
        simpleProcessor = new SimpleCrateStorageOperationProcessor();
    }

    @Override
    protected void execute() {
        List<String> arrangementLines = new ArrayList<>();
        List<Instruction> instructions = new ArrayList<>();

        boolean isArrangementsSection = true;
        for (String line : lines) {
            if (line.isBlank()) {
                isArrangementsSection = false;
                continue;
            }

            if (isArrangementsSection) {
                arrangementLines.add(line);
            } else {
                instructions.add(Instruction.fromInstructionLine(line));
            }
        }

        CrateStorage crateStorage = CrateStorage.fromArrangementLines(arrangementLines);

        simpleProcessor.executeInstructions(crateStorage, instructions);

        // ---

        System.out.printf(
            "1. After rearrangement, the labels of the uppermost crates spell out %s.\n\n",
            crateStorage.getStacks().values().stream()
                .filter(crates -> crates.peek() != null)
                .map(crates -> crates.peek().getLabel())
                .reduce("", (s, c) -> s + c, (s1, s2) -> s1 + s2)
        );
    }
}
