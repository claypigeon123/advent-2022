package com.cp.advent2022.data.day5.processor.impl;

import com.cp.advent2022.data.day5.Crate;
import com.cp.advent2022.data.day5.CrateStorage;
import com.cp.advent2022.data.day5.Instruction;
import com.cp.advent2022.data.day5.processor.CrateStorageOperationProcessor;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CrateMover9001 implements CrateStorageOperationProcessor {

    @Override
    public void executeInstructions(CrateStorage crateStorage, List<Instruction> instructions) {
        for (Instruction instruction : instructions) {
            var from = crateStorage.getStacks().get(instruction.getFrom());
            var to = crateStorage.getStacks().get(instruction.getTo());

            Deque<Crate> craneHands = new LinkedList<>();

            for (int i = 0; i < instruction.getNToMove() && from.peek() != null; i++) {
                craneHands.offer(from.pop());
            }

            while (craneHands.peek() != null) {
                to.push(craneHands.removeLast());
            }
        }
    }
}
