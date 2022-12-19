package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.aspect.resloader.LoadAdventResource;
import com.cp.advent2022.data.day18.SimpleCube;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.ArrayList;
import java.util.List;

@Component
@LoadAdventResource(18)
@Command(name = "day18", mixinStandardHelpOptions = true)
public class Day18Api extends DayApi {

    @Override
    protected void execute() throws Exception {
        List<SimpleCube> cubes = new ArrayList<>();

        for (String line : lines) {
            cubes.add(SimpleCube.fromString(line));
        }

        long allExposedSides = 0;
        for (SimpleCube checked : cubes) {
            allExposedSides += checked.getExposedSides(cubes);
        }

        long outwardExposedSides = 0;
        for (SimpleCube checked : cubes) {

        }

        // ---

        System.out.printf("1. The total number of exposed sides is %d.\n\n", allExposedSides);

        System.out.printf("2. The total number of outward-facing exposed sides is %d.\n\n", outwardExposedSides);
    }
}
