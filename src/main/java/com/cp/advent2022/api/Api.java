package com.cp.advent2022.api;

import picocli.CommandLine.IExitCodeGenerator;

public abstract class Api implements Runnable, IExitCodeGenerator {

    protected int exitCode = 0;

    protected void initialize() {}

    protected abstract void execute() throws Exception;

    protected void close() {}

    @Override
    public final void run() {
        try {
            initialize();
            execute();
            close();
        } catch (Exception e) {
            exitCode = -1;
        }
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }
}
