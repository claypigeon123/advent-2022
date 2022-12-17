package com.cp.advent2022.data.day14;

import lombok.Data;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Data
public class Cave {
    private static final char SPACE = '.';
    private static final char SAND = 'o';
    private static final char ROCK = '#';

    private static final int SAND_ORIGIN_Y = 0, SAND_ORIGIN_X = 499;

    private char[][] terrain;
    private int settledSandCounter;
    private boolean filled;

    public Cave() {
        this.terrain = new char[200][1000];
        for (char[] row : terrain) {
            Arrays.fill(row, SPACE);
        }

        this.settledSandCounter = 0;
        this.filled = false;
    }

    public void print() {
        try (PrintWriter pw = new PrintWriter(new BufferedOutputStream(Files.newOutputStream(Path.of("field.txt"))), true)) {
            for (char[] row : terrain) {
                for (char c : row) {
                    pw.print(c);
                }
                pw.print("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void applyRockVectorGroup(List<Vector> vectors) {
        for (Vector vec : vectors) {
            int fromY = Math.min(vec.getFromY(), vec.getToY());
            int fromX = Math.min(vec.getFromX(), vec.getToX());

            int toY = Math.max(vec.getFromY(), vec.getToY());
            int toX = Math.max(vec.getFromX(), vec.getToX());

            for (int y = fromY - 1; y < toY; y++) {
                for (int x = fromX - 1; x < toX; x++) {
                    terrain[y][x] = ROCK;
                }
            }
        }
    }

    public void trickleNewSandUnit() {
        int sandY = SAND_ORIGIN_Y, sandX = SAND_ORIGIN_X;
        boolean settled = false;

        while (!settled) {
            if (sandY == terrain.length - 1) {
                filled = true;
                break;
            }

            if (isNotBlocked(sandY + 1, sandX)) {
                sandY = sandY + 1;
            } else if (isNotBlocked(sandY + 1, sandX - 1)) {
                sandY = sandY + 1;
                sandX = sandX - 1;
            } else if (isNotBlocked(sandY + 1, sandX + 1)) {
                sandY = sandY + 1;
                sandX = sandX + 1;
            } else {
                settledSandCounter++;
                terrain[sandY][sandX] = SAND;
                settled = true;
            }
        }
    }

    private boolean isNotBlocked(int y, int x) {
        char atPos = terrain[y][x];
        return atPos != ROCK && atPos != SAND;
    }
}
