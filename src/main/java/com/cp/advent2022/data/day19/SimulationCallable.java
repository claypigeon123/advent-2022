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

    @Override
    public Integer call() {
        System.out.printf("Starting blueprint ID [%s]\n", blueprint.getId());
        Map<RobotType, Integer> maxToKeep = Map.of(
            RobotType.ORE, blueprint.getHighestCost(OreType.ORE),
            RobotType.CLAY, blueprint.getHighestCost(OreType.CLAY),
            RobotType.OBSIDIAN, blueprint.getHighestCost(OreType.OBSIDIAN)
        );

        List<Simulation> completed = new ArrayList<>();

        Set<Simulation> seenStates = new HashSet<>();
        Queue<Simulation> queue = new LinkedList<>();
        Simulation starter = Simulation.fromBlueprint(blueprint);
        queue.offer(starter);

        int max = -1;

        while (queue.peek() != null) {
            Simulation sim = queue.poll();

            if (sim.getMinute() == forMinutes) {
                completed.add(sim);
                continue;
            }

            List<Simulation> nextRound = new ArrayList<>();

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

            for (Simulation next : nextRound) {
                if (seenStates.contains(next)) continue;
                if (next.getGeodes() > 0) {
                    max = next.getGeodes();
                }
                if (max != -1 && next.getGeodes() < max / 2) continue;

                seenStates.add(next);
                queue.offer(next);
            }
        }

        System.out.printf("Done with blueprint ID [%s]\n", blueprint.getId());
        return completed.stream()
            .mapToInt(s -> s.getBlueprint().getId() * s.getGeodes())
            .max()
            .orElseThrow();
    }
}
