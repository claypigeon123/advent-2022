package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.aspect.resloader.LoadAdventResource;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.ArrayList;
import java.util.List;

@Component
@LoadAdventResource
@Command(name = "day8", mixinStandardHelpOptions = true)
public class Day8Api extends DayApi {

    public Day8Api() {
        super(8);
    }

    @Override
    protected void execute() {
        List<List<Byte>> treeLayout = new ArrayList<>();

        for (String line : lines) {
            List<Byte> treeLine = new ArrayList<>();

            for (Character c : line.toCharArray()) {
                byte height = Byte.parseByte(c.toString());
                treeLine.add(height);
            }

            treeLayout.add(treeLine);
        }

        int visibleCount = 0;
        for (int y = 0; y < treeLayout.size(); y++) {
            List<Byte> treeLine = treeLayout.get(y);

            for (int x = 0; x < treeLine.size(); x++) {
                if (y == 0 || y == treeLayout.size() - 1 || x == 0 || x == treeLine.size() - 1) {
                    visibleCount++;
                    continue;
                }

                if (isVisible(treeLayout, y, x)) {
                    visibleCount++;
                }
            }
        }

        int highestScenicScore = 0;
        for (int y = 0; y < treeLayout.size(); y++) {
            List<Byte> treeLine = treeLayout.get(y);

            for (int x = 0; x < treeLine.size(); x++) {
                int scenicScore = calculateScenicScore(treeLayout, y, x);

                if (scenicScore > highestScenicScore) {
                    highestScenicScore = scenicScore;
                }
            }
        }

        // --

        System.out.printf("1. The number of trees visible from the outside is %d.\n\n", visibleCount);

        System.out.printf("2. The highest scenic score for any tree in the layout is %d.\n\n", highestScenicScore);
    }

    private boolean isVisible(List<List<Byte>> treeLayout, int checkY, int checkX) {
        boolean visible = false;
        byte compareHeight = treeLayout.get(checkY).get(checkX);

        for (int y = checkY + 1; y < treeLayout.size() && !visible; y++) {
            if (treeLayout.get(y).get(checkX) >= compareHeight) {
                break;
            }
            if (y == treeLayout.size() - 1) {
                visible = true;
                break;
            }
        }

        for (int y = checkY - 1; y >= 0 && !visible; y--) {
            if (treeLayout.get(y).get(checkX) >= compareHeight) {
                break;
            }
            if (y == 0) {
                visible = true;
                break;
            }
        }

        boolean onWestSide = true;
        List<Byte> treeLine = treeLayout.get(checkY);
        for (int x = 0; x < treeLine.size() && !visible; x++) {
            if (x == checkX) {
                onWestSide = false;
                continue;
            }

            if (treeLine.get(x) >= compareHeight) {
                if (onWestSide) {
                    x = checkX - 1;
                    continue;
                } else {
                    break;
                }
            }

            if (x == checkX - 1) {
                visible = true;
                break;
            }
            if (x == treeLine.size() - 1) {
                visible = true;
                break;
            }
        }

        return visible;
    }

    private int calculateScenicScore(List<List<Byte>> treeLayout, int checkY, int checkX) {
        byte compareHeight = treeLayout.get(checkY).get(checkX);

        int southScore = 0;
        for (int y = checkY + 1; y < treeLayout.size(); y++) {
            southScore++;
            if (treeLayout.get(y).get(checkX) >= compareHeight) {
                break;
            }
        }

        int northScore = 0;
        for (int y = checkY - 1; y >= 0; y--) {
            northScore++;
            if (treeLayout.get(y).get(checkX) >= compareHeight) {
                break;
            }
        }

        int eastScore = 0;
        List<Byte> treeLine = treeLayout.get(checkY);
        for (int x = checkX + 1; x < treeLine.size(); x++) {
            eastScore++;
            if (treeLine.get(x) >= compareHeight) {
                break;
            }
        }

        int westScore = 0;
        for (int x = checkX - 1; x >= 0; x--) {
            westScore++;
            if (treeLine.get(x) >= compareHeight) {
                break;
            }
        }

        return southScore * northScore * westScore * eastScore;
    }
}
