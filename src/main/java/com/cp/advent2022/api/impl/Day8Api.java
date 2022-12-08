package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.component.AdventResourceLoader;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.ArrayList;
import java.util.List;

@Component
@Command(name = "day8", mixinStandardHelpOptions = true)
public class Day8Api extends DayApi {

    public Day8Api(AdventResourceLoader adventResourceLoader) {
        super(8, adventResourceLoader);
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

        // --

        System.out.printf("1. The number of trees visible from the outside is %d.\n\n", visibleCount);
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
                System.out.printf("y: %d, x: %d - visible from the SOUTH\n\n", checkY, checkX);
                break;
            }
        }

        for (int y = checkY - 1; y >= 0 && !visible; y--) {
            if (treeLayout.get(y).get(checkX) >= compareHeight) {
                break;
            }
            if (y == 0) {
                visible = true;
                System.out.printf("y: %d, x: %d - visible from the NORTH\n\n", checkY, checkX);
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
                System.out.printf("y: %d, x: %d - visible from the WEST\n\n", checkY, checkX);
                break;
            }
            if (x == treeLine.size() - 1) {
                visible = true;
                System.out.printf("y: %d, x: %d - visible from the EAST\n\n", checkY, checkX);
                break;
            }
        }

        return visible;
    }

    private void printTreeLayout(List<List<Byte>> treeLayout) {
        for (List<Byte> treeLine : treeLayout) {
            for (Byte height : treeLine) {
                System.out.print(height);
            }
            System.out.print("\n");
        }
    }
}
