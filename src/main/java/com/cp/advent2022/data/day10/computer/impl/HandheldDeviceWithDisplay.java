package com.cp.advent2022.data.day10.computer.impl;

import com.cp.advent2022.data.day10.Statement;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class HandheldDeviceWithDisplay extends HandheldDevice {
    private static final int SCREEN_WIDTH = 40;

    private final List<char[]> pixels;

    public HandheldDeviceWithDisplay(Set<Integer> cyclesToCheck) {
        super(cyclesToCheck);
        this.pixels = new ArrayList<>();
    }

    public void renderScreen(PrintStream to) {
        for (char[] y : pixels) {
            for (char x : y) {
                to.print(x);
            }
            to.println();
        }
    }

    @Override
    protected void executeStatement(Statement statement) {
        int row = (cycle - 1) / SCREEN_WIDTH;

        while (pixels.size() <= row) {
            char[] newRow = new char[SCREEN_WIDTH];
            Arrays.fill(newRow, '.');
            pixels.add(newRow);
        }

        char[] currentRow = pixels.get(row);

        int horizontalPosition = (cycle - 1) - (row * SCREEN_WIDTH);

        if (Math.abs(xRegister - horizontalPosition) <= 1) {
            currentRow[horizontalPosition] = '#';
        }

        super.executeStatement(statement);
    }
}
