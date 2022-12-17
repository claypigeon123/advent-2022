package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.aspect.resloader.LoadAdventResource;
import com.cp.advent2022.data.day14.Cave;
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
        Cave cave = new Cave();

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

                cave.applyRockVectorGroup(vectors);
            }
        }

        while (!cave.isFilled()) {
            cave.trickleNewSandUnit();
        }
        cave.print();

        // ---

        System.out.printf("1. %d units of sand can settle before the rest falls into the void.\n", cave.getSettledSandCounter());
    }

    private Scanner initScanner(String input) {
        Scanner scan = new Scanner(input);
        scan.useDelimiter(",");
        return scan;
    }
}
