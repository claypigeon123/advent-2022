package com.cp.advent2022.api;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.IExitCodeGenerator;

@Slf4j
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
        } catch (Exception e) {
            exitCode = -1;
            log.error("Runtime error: {}", e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }
}
