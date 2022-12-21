package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.aspect.resloader.LoadAdventResource;
import com.cp.advent2022.data.day19.Blueprint;
import com.cp.advent2022.data.day19.SimulationCallable;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
@LoadAdventResource(19)
@Command(name = "day19", mixinStandardHelpOptions = true)
public class Day19Api extends DayApi {

    private final ExecutorService executor = Executors.newWorkStealingPool();

    @Override
    protected void execute() throws Exception {
        List<SimulationCallable> simulationCallables = new ArrayList<>();

        for (String line : lines) {
            Blueprint blueprint = Blueprint.fromString(line);
            simulationCallables.add(new SimulationCallable(24, blueprint));
        }

        List<Future<Integer>> futures = executor.invokeAll(simulationCallables);
        List<Integer> results = new ArrayList<>();

        for (var future : futures) {
            Integer res =  future.get();
            if (res != null) results.add(res);
        }

        int qualitySum = results.stream()
            .mapToInt(i -> i)
            .sum();

        // ---

        System.out.printf("1. The summed quality of all blueprints is %d.\n\n", qualitySum);
    }
}
