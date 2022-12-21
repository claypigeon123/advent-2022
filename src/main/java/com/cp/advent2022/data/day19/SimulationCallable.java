package com.cp.advent2022.data.day19;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.concurrent.Callable;

@Data
@RequiredArgsConstructor
public class SimulationCallable implements Callable<Integer> {

    private final int forMinutes;
    private final Blueprint blueprint;
    private final boolean returnRaw;

    @Override
    public Integer call() {
        Map<RobotType, Integer> maxToKeep = Map.of(
            RobotType.ORE, blueprint.getHighestCost(OreType.ORE),
            RobotType.CLAY, blueprint.getHighestCost(OreType.CLAY),
            RobotType.OBSIDIAN, blueprint.getHighestCost(OreType.OBSIDIAN)
        );

        Set<Simulation> seenStates = new HashSet<>();
        Queue<Simulation> queue = new LinkedList<>();
        Simulation starter = Simulation.fromBlueprint(blueprint);
        queue.offer(starter);

        int max = Integer.MIN_VALUE;

        while (queue.peek() != null) {
            Simulation sim = queue.poll();

            if (sim.getMinute() == forMinutes) {
                continue;
            }

            List<Simulation> nextRound = new ArrayList<>();

            if (sim.canConstruct(RobotType.GEODE)) {
                Simulation copy = Simulation.newFrom(sim);
                copy.constructAndAdvanceTime(RobotType.GEODE);
                nextRound.add(copy);
            } else {
                for (RobotType robotType : RobotType.values()) {
                    if (robotType != RobotType.GEODE && sim.getRobots().get(robotType) >= maxToKeep.get(robotType)) {
                        continue;
                    }

                    if (sim.canConstruct(robotType)) {
                        Simulation copy = Simulation.newFrom(sim);
                        copy.constructAndAdvanceTime(robotType);
                        nextRound.add(copy);
                    }
                }

                Simulation copy = Simulation.newFrom(sim);
                copy.advanceTime();
                nextRound.add(copy);
            }

            for (Simulation next : nextRound) {
                if (seenStates.contains(next)) continue;

                int geodesCollected = next.getGeodes();
                if (geodesCollected > max) {
                    max = geodesCollected;
                }
                int geodeRobots = next.getRobots().get(RobotType.GEODE);
                int timeRemaining = forMinutes - next.getMinute();
                int tDelta = 0;
                for (int i = 1; i <= timeRemaining - 1; i++) {
                    tDelta += i;
                }
                if ((geodesCollected + geodeRobots * timeRemaining + tDelta) <= max) {
                    continue;
                }

                seenStates.add(next);
                queue.offer(next);
            }
        }

        return returnRaw ? max : max * blueprint.getId();
    }
}
