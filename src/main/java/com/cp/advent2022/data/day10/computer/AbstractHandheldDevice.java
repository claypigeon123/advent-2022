package com.cp.advent2022.data.day10.computer;

import com.cp.advent2022.data.day10.Statement;
import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;

@Data
public abstract class AbstractHandheldDevice implements Callable<Long> {
    protected long xRegister;
    protected long cycle;

    protected Queue<Statement> statementQueue;
    protected Set<Long> cyclesToExtract;

    public AbstractHandheldDevice(Set<Long> cyclesToExtract) {
        this.xRegister = 1;
        this.cycle = 0;
        this.statementQueue = new LinkedList<>();
        this.cyclesToExtract = cyclesToExtract;
    }

    @Override
    public Long call() {
        long sumOfCheckedCycles = 0;

        while (statementQueue.size() > 0) {
            cycle++;
            System.out.printf("[%d] CYCLE START --- Register X: %d\n", cycle, xRegister);

            if (cyclesToExtract.contains(cycle)) {
                sumOfCheckedCycles += xRegister * cycle;
            }

            tick();
            System.out.printf("[%d] CYCLE END   --- Register X: %d\n", cycle, xRegister);
        }

        return sumOfCheckedCycles;
    }

    public abstract void queueStatement(Statement statement);

    protected abstract void executeStatement(Statement statement);

    protected void tick() {
        Statement statement = statementQueue.poll();
        if (statement == null) return;

        executeStatement(statement);
    }
}
