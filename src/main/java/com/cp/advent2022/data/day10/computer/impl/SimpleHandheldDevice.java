package com.cp.advent2022.data.day10.computer.impl;

import com.cp.advent2022.data.day10.Instruction;
import com.cp.advent2022.data.day10.Statement;
import com.cp.advent2022.data.day10.computer.AbstractHandheldDevice;

import java.util.Objects;
import java.util.Set;

public class SimpleHandheldDevice extends AbstractHandheldDevice {

    public SimpleHandheldDevice(Set<Long> cyclesToExtract) {
        super(cyclesToExtract);
    }

    @Override
    public void queueStatement(Statement statement) {
        switch (statement.getInstruction()) {
            case NO_OPERATION -> statementQueue.offer(null);
            case ADD_X -> {
                statementQueue.offer(null);
                statementQueue.offer(statement);
            }
        }
    }

    @Override
    protected void executeStatement(Statement statement) {
        if (Objects.requireNonNull(statement.getInstruction()) == Instruction.ADD_X) {
            this.xRegister = xRegister + Integer.parseInt(statement.getArguments().get(0));
        }
    }
}
