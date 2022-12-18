package com.cp.advent2022.data.day16;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    protected Valve at;
    protected int minute;
    protected long accumulatedFlow;
    protected Set<String> opened;

    public static List<Route> nextFrom(Route route, Map<String, Valve> valveMap, Map<String, Valve> valvesOfInterest) {
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

        List<Route> nextRoutes = new ArrayList<>();

        route.getAt().getLeadsTo().stream()
            .map(valveMap::get)
            .forEach(valve -> nextRoutes.add(new Route(valve, route.minute, route.accumulatedFlow, new HashSet<>(route.opened))));

        return nextRoutes;
    }
}
