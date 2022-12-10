package com.cp.advent2022.data.day10.computer;

import com.cp.advent2022.data.day10.Statement;
import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

@Data
public abstract class AbstractHandheldDevice implements Runnable {
    protected int xRegister;
    protected int cycle;

    protected Queue<Statement> statementQueue;

    public AbstractHandheldDevice() {
        this.xRegister = 1;
        this.cycle = 0;
        this.statementQueue = new LinkedList<>();
    }

    public abstract void queueStatement(Statement statement);

    protected abstract void executeStatement(Statement statement);

    protected void tick() {
        Statement statement = statementQueue.poll();
        if (statement == null) return;

        executeStatement(statement);
    }
}
