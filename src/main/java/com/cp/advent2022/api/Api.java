package com.cp.advent2022.api;

public abstract class Api implements Runnable {

    protected void initialize() {}

    protected abstract void execute();

    protected void close() {}

    @Override
    public final void run() {
        initialize();
        execute();
        close();
    }
}
