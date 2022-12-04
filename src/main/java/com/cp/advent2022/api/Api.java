package com.cp.advent2022.api;

import picocli.CommandLine.IExitCodeGenerator;

public abstract class Api implements Runnable, IExitCodeGenerator {

    protected int exitCode = 0;

    protected void initialize() {}

    protected abstract void execute();

    protected void close() {}

    @Override
    public final void run() {
        initialize();
        execute();
        close();
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }
}
