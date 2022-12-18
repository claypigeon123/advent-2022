package com.cp.advent2022.data.day16;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class Route {
    private Valve at;
    private int minute;
    private long accumulatedFlow;
    private Set<String> opened;

    public static List<Route> nextFrom(Route route, Map<String, Valve> valveMap, Map<String, Valve> valvesOfInterest) {
        List<Route> nextRoutes = new ArrayList<>();

        route.minute++;

        int sum = route.opened.stream()
            .map(valveMap::get)
            .mapToInt(Valve::getFlowRate)
            .sum();
        route.accumulatedFlow += sum;

        if (route.opened.containsAll(valvesOfInterest.keySet())) {
            return List.of(route);
        }

        if (route.at.getFlowRate() > 0 && !route.opened.contains(route.at.getLabel())) {
            route.opened.add(route.at.getLabel());
            return List.of(route);
        }

        route.getAt().getLeadsTo().stream()
            .map(valveMap::get)
            .forEach(valve -> nextRoutes.add(new Route(valve, route.minute, route.accumulatedFlow, new HashSet<>(route.opened))));

        return nextRoutes;
    }
}
