package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.aspect.resloader.LoadAdventResource;
import com.cp.advent2022.data.day16.Route;
import com.cp.advent2022.data.day16.Valve;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.*;
import java.util.stream.Collectors;

@Component
@LoadAdventResource(16)
@Command(name = "day16", mixinStandardHelpOptions = true)
public class Day16Api extends DayApi {

    @Override
    protected void execute() throws Exception {
        Map<String, Valve> valveMap = new HashMap<>();
        Map<String, Valve> valvesOfInterest = new HashMap<>();

        for (String line : lines) {
            Valve valve = Valve.fromString(line);
            valveMap.put(valve.getLabel(), valve);
            if (valve.getFlowRate() > 0) {
                valvesOfInterest.put(valve.getLabel(), valve);
            }
        }

        List<Route> routes = new ArrayList<>();
        routes.add(new Route(valveMap.get("AA"), 0, 0, new HashSet<>()));

        while (routes.stream().anyMatch(route -> route.getMinute() != 30)) {
            routes = routes.stream()
                .flatMap(route -> Route.nextFrom(route, valveMap, valvesOfInterest).stream())
                .sorted(Comparator.comparingLong(Route::getAccumulatedFlow).reversed())
                .limit(2000)
                .collect(Collectors.toList());
        }

        // ---

        System.out.printf("1. The best route releases %d pressure by minute 30.\n\n", routes.get(0).getAccumulatedFlow());
    }
}
