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

            if (sim.getGeodes() > 0) {
                max = sim.getGeodes();
            }

            if (sim.getMinute() == forMinutes) {
                completed.add(sim);
                continue;
            }

            List<Simulation> nextRound = new ArrayList<>();

            if (sim.canConstruct(RobotType.GEODE)) {
                Simulation copy = Simulation.newFrom(sim);
                copy.constructAndAdvanceTime(RobotType.GEODE);
                nextRound.add(copy);
            }
            if (sim.getRobots().get(RobotType.OBSIDIAN) < maxToKeep.get(RobotType.OBSIDIAN) && sim.canConstruct(RobotType.OBSIDIAN)) {
                Simulation copy = Simulation.newFrom(sim);
                copy.constructAndAdvanceTime(RobotType.OBSIDIAN);
                nextRound.add(copy);
            }
            if (sim.getRobots().get(RobotType.CLAY) < maxToKeep.get(RobotType.CLAY) && sim.canConstruct(RobotType.CLAY)) {
                Simulation copy = Simulation.newFrom(sim);
                copy.constructAndAdvanceTime(RobotType.CLAY);
                nextRound.add(copy);
            }
            if (sim.getRobots().get(RobotType.ORE) < maxToKeep.get(RobotType.ORE) && sim.canConstruct(RobotType.ORE)) {
                Simulation copy = Simulation.newFrom(sim);
                copy.constructAndAdvanceTime(RobotType.ORE);
                nextRound.add(copy);
            }

            Simulation copy = Simulation.newFrom(sim);
            copy.advanceTime();
            nextRound.add(copy);

            /*for (RobotType robotType : RobotType.values()) {
                if (robotType != RobotType.GEODE && sim.getRobots().get(robotType) >= maxToKeep.get(RobotType.ORE)) {
                    continue;
                }

                if (sim.canConstruct(robotType)) {
                    Simulation copy = Simulation.newFrom(sim);
                    copy.constructAndAdvanceTime(robotType);

                    if (!seenStates.contains(copy)) nextRound.add(copy);
                }
            }

            Simulation copy = Simulation.newFrom(sim);
            copy.advanceTime();

            if (!seenStates.contains(copy)) nextRound.add(copy);*/

            for (Simulation next : nextRound) {
                if (seenStates.contains(next)) continue;
                if (max != -1 && next.getGeodes() < max / 2) continue;

                seenStates.add(next);
                queue.offer(next);
            }

            /*Simulation sim = queue.poll();
            if (sim.getMinute() == forMinutes) {
                completed.add(sim);
                continue;
            }

            if (sim.getMinute() >= 19) {
                if (sim.getGeodes() > max) {
                    max = sim.getGeodes();
                }
            }

            List<Simulation> toSubmit = new ArrayList<>();

            for (RobotType robotType : RobotType.values()) {
                if (sim.canConstruct(robotType)) {
                    Simulation copy = Simulation.newFrom(sim);
                    copy.constructAndAdvanceTime(robotType);

                    if (!seenStates.contains(copy)) toSubmit.add(copy);
                }
            }

            Simulation copy = Simulation.newFrom(sim);
            copy.advanceTime();

            if (!seenStates.contains(copy)) toSubmit.add(copy);

            for (Simulation next : toSubmit) {
                if (next.getMinute() >= 21) {
                    if (next.getGeodes() < max) {
                        continue;
                    }
                }

                queue.offer(next);
                seenStates.add(next);
            }*/
        }

        System.out.printf("Done with blueprint ID [%s]\n", blueprint.getId());
        return completed.stream()
            .filter(s -> s.getGeodes() > 0)
            .mapToInt(s -> s.getBlueprint().getId() * s.getGeodes())
            .max()
            .orElseThrow();
    }
}
