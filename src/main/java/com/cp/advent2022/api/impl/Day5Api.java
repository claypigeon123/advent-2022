package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.aspect.resloader.LoadAdventResource;
import com.cp.advent2022.data.day5.CrateStorage;
import com.cp.advent2022.data.day5.Instruction;
import com.cp.advent2022.data.day5.processor.CrateStorageOperationProcessor;
import com.cp.advent2022.data.day5.processor.impl.CrateMover9000;
import com.cp.advent2022.data.day5.processor.impl.CrateMover9001;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.ArrayList;
import java.util.List;

@Component
@LoadAdventResource
@Command(name = "day5", mixinStandardHelpOptions = true)
public class Day5Api extends DayApi {

    private final CrateStorageOperationProcessor crateMover9000;
    private final CrateStorageOperationProcessor crateMover9001;

    public Day5Api() {
        super(5);
        crateMover9000 = new CrateMover9000();
        crateMover9001 = new CrateMover9001();
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

        CrateStorage firstCrateStorage = CrateStorage.fromArrangementLines(arrangementLines);
        crateMover9000.executeInstructions(firstCrateStorage, instructions);

        CrateStorage secondCrateStorage = CrateStorage.fromArrangementLines(arrangementLines);
        crateMover9001.executeInstructions(secondCrateStorage, instructions);

        // ---

        System.out.printf(
            "1. After rearrangement with the CrateMover 9000, the labels of the uppermost crates spell out %s.\n\n",
            readUppermostLabelsInCrateStorage(firstCrateStorage)
        );

        System.out.printf(
            "2. After rearrangement with the CrateMover 9001, the labels of the uppermost crates spell out %s.\n\n",
            readUppermostLabelsInCrateStorage(secondCrateStorage)
        );
    }

    private String readUppermostLabelsInCrateStorage(CrateStorage crateStorage) {
        return crateStorage.getStacks().values().stream()
            .filter(crates -> crates.peek() != null)
            .map(crates -> crates.peek().getLabel())
            .reduce("", (s, c) -> s + c, (s1, s2) -> s1 + s2);
    }
}
