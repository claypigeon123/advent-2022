package com.cp.advent2022.data.day5.processor;

import com.cp.advent2022.data.day5.CrateStorage;
import com.cp.advent2022.data.day5.Instruction;

import java.util.List;

public interface CrateStorageOperationProcessor {
    void executeInstructions(CrateStorage crateStorage, List<Instruction> instructions);
}
