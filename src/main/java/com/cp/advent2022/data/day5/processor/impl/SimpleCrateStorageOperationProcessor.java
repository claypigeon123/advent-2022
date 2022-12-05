package com.cp.advent2022.data.day5.processor.impl;

import com.cp.advent2022.data.day5.Crate;
import com.cp.advent2022.data.day5.CrateStorage;
import com.cp.advent2022.data.day5.Instruction;
import com.cp.advent2022.data.day5.processor.CrateStorageOperationProcessor;

import java.util.List;

public class SimpleCrateStorageOperationProcessor implements CrateStorageOperationProcessor {

    @Override
    public void executeInstructions(CrateStorage crateStorage, List<Instruction> instructions) {
        for (Instruction instruction : instructions) {
            var from = crateStorage.getStacks().get(instruction.getFrom());
            var to = crateStorage.getStacks().get(instruction.getTo());

            for (int i = 0; (i < instruction.getNToMove()) && from.peek() != null; i++) {
                Crate crate = from.pop();
                to.push(crate);
            }
        }
    }
}
