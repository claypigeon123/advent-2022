package com.cp.advent2022.data.day10.computer.impl;

import com.cp.advent2022.data.day10.Instruction;
import com.cp.advent2022.data.day10.Statement;
import com.cp.advent2022.data.day10.computer.AbstractHandheldDevice;
import lombok.Getter;

import java.util.Set;

public class HandheldDevice extends AbstractHandheldDevice {

    protected final Set<Integer> cyclesToExtract;

    @Getter
    protected int sumOfCheckedCycles;

    public HandheldDevice(Set<Integer> cyclesToExtract) {
        this.cyclesToExtract = cyclesToExtract;
        this.sumOfCheckedCycles = 0;
    }

    @Override
    public void run() {
        while (statementQueue.peek() != null) {
            cycle++;
            if (cyclesToExtract.contains(cycle)) {
                sumOfCheckedCycles += xRegister * cycle;
            }
            tick();
        }
    }

    @Override
    public void queueStatement(Statement statement) {
        switch (statement.getInstruction()) {
            case NO_OPERATION -> statementQueue.offer(Statement.noOp());
            case ADD_X -> {
                statementQueue.offer(Statement.noOp());
                statementQueue.offer(statement);
            }
        }
    }

    @Override
    protected void executeStatement(Statement statement) {
        if (statement.getInstruction() == Instruction.ADD_X) {
            this.xRegister = xRegister + Integer.parseInt(statement.getArguments().get(0));
        }
    }
}
