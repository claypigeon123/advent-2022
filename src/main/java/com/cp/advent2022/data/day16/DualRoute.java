package com.cp.advent2022.data.day16;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.*;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DualRoute extends Route {

    private Valve elephantAt;

    public DualRoute(Valve at, Valve elephantAt, int minute, long accumulatedFlow, Set<String> opened) {
        super(at, minute, accumulatedFlow, opened);
        this.elephantAt = elephantAt;
    }

    public static List<DualRoute> nextFrom(DualRoute route, Map<String, Valve> valveMap, Map<String, Valve> valvesOfInterest) {
        route.minute++;

        int sum = route.opened.stream()
            .map(valveMap::get)
            .mapToInt(Valve::getFlowRate)
            .sum();
        route.accumulatedFlow += sum;

        if (route.opened.containsAll(valvesOfInterest.keySet())) {
            return List.of(route);
        }

        List<DualRoute> nextRoutes = new ArrayList<>();

        boolean iStay = false;
        if (route.at.getFlowRate() > 0 && !route.opened.contains(route.at.getLabel())) {
            route.opened.add(route.at.getLabel());
            iStay = true;
        }

        boolean elephantStays = false;
        if (route.elephantAt.getFlowRate() > 0 && !route.opened.contains(route.elephantAt.getLabel())) {
            route.opened.add(route.elephantAt.getLabel());
            elephantStays = true;
        }

        if (elephantStays && iStay) {
            nextRoutes.add(route);
        } else if (!iStay && elephantStays) {
            route.at.getLeadsTo().stream()
                .map(valveMap::get)
                .forEach(valve -> nextRoutes.add(new DualRoute(valve, route.elephantAt, route.minute, route.accumulatedFlow, new HashSet<>(route.opened))));
        } else if (iStay) {
            route.elephantAt.getLeadsTo().stream()
                .map(valveMap::get)
                .forEach(valve -> nextRoutes.add(new DualRoute(route.at, valve, route.minute, route.accumulatedFlow, new HashSet<>(route.opened))));
        } else {
            for (String iGo : route.at.getLeadsTo()) {
                for (String elephantGoes : route.elephantAt.getLeadsTo()) {
                    nextRoutes.add(new DualRoute(valveMap.get(iGo), valveMap.get(elephantGoes), route.minute, route.accumulatedFlow, new HashSet<>(route.opened)));
                }
            }
        }

        return nextRoutes;
    }
}
