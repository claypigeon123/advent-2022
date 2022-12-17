package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.aspect.resloader.LoadAdventResource;
import com.cp.advent2022.data.day14.BottomlessCave;
import com.cp.advent2022.data.day14.FlooredCave;
import com.cp.advent2022.data.day14.Vector;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
@LoadAdventResource(14)
@Command(name = "day14", mixinStandardHelpOptions = true)
public class Day14Api extends DayApi {

    @Override
    protected void execute() throws Exception {
        BottomlessCave bottomlessCave = new BottomlessCave();
        FlooredCave flooredCave = new FlooredCave();

        for (String line : lines) {
            line = line.replaceAll(" -> ", ",");
            try (Scanner scan = initScanner(line)) {
                List<Vector> vectors = new ArrayList<>();
                Integer prevY = null, prevX = null;

                while (scan.hasNext()) {
                    int x = scan.nextInt(); int y = scan.nextInt();

                    if (prevY == null) {
                        prevY = y;
                        prevX = x;
                        continue;
                    }

                    Vector vector = new Vector(prevY, prevX, y, x);
                    vectors.add(vector);
                    prevY = y;
                    prevX = x;
                }

                bottomlessCave.applyRockVectorGroup(vectors);
                flooredCave.applyRockVectorGroup(vectors);
            }
        }

        flooredCave.drawFloor();

        while (!bottomlessCave.isFilled()) {
            bottomlessCave.trickleNewSandUnit();
        }

        while (!flooredCave.isFilled()) {
            flooredCave.trickleNewSandUnit();
        }

        // ---

        System.out.printf("1. %d units of sand can settle in the bottomless cave before the rest falls into the void.\n\n", bottomlessCave.getSettledSandCounter());

        System.out.printf("2. %d units of sand can settle in the floored cave until the outlet is blocked.\n\n", flooredCave.getSettledSandCounter());
    }

    private Scanner initScanner(String input) {
        Scanner scan = new Scanner(input);
        scan.useDelimiter(",");
        return scan;
    }
}
