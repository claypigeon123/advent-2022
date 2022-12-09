package com.cp.advent2022.data.day9.processor;

import com.cp.advent2022.data.day9.GridItem;
import com.cp.advent2022.data.day9.Instruction;

import java.util.List;
import java.util.Set;

public interface GridKnotInstructionProcessor {
    Set<String> process(List<GridItem> gridItems, Instruction instruction);
}
