package com.cp.advent2022.data.day19;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Simulation {

    private int minute;

    private Blueprint blueprint;

    private Map<RobotType, Integer> robots;

    private int ores;
    private int clay;
    private int obsidian;
    private int geodes;

    public static Simulation fromBlueprint(Blueprint blueprint) {
        var robots = new HashMap<>(Map.of(
            RobotType.ORE, 1,
            RobotType.CLAY, 0,
            RobotType.OBSIDIAN, 0,
            RobotType.GEODE, 0
        ));
        return new Simulation(0, blueprint, robots, 0, 0, 0, 0);
    }

    public static Simulation newFrom(Simulation s) {
        return new Simulation(s.minute, s.blueprint, new HashMap<>(Map.copyOf(s.robots)), s.ores, s.clay, s.obsidian, s.geodes);
    }

    public void advanceTime() {
        minute++;

        ores += robots.get(RobotType.ORE);
        clay += robots.get(RobotType.CLAY);
        obsidian += robots.get(RobotType.OBSIDIAN);
        geodes += robots.get(RobotType.GEODE);
    }

    public void constructAndAdvanceTime(RobotType robotType) {
        advanceTime();

        switch (robotType) {
            case ORE -> {
                ores -= blueprint.getOreRobotCost().getOreCost();
                robots.put(RobotType.ORE, robots.get(RobotType.ORE) + 1);
            }
            case CLAY -> {
                ores -= blueprint.getClayRobotCost().getOreCost();
                robots.put(RobotType.CLAY, robots.get(RobotType.CLAY) + 1);
            }
            case OBSIDIAN -> {
                ores -= blueprint.getObsidianRobotCost().getOreCost();
                clay -= blueprint.getObsidianRobotCost().getClayCost();
                robots.put(RobotType.OBSIDIAN, robots.get(RobotType.OBSIDIAN) + 1);
            }
            case GEODE -> {
                ores -= blueprint.getGeodeRobotCost().getOreCost();
                obsidian -= blueprint.getGeodeRobotCost().getObsidianCost();
                robots.put(RobotType.GEODE, robots.get(RobotType.GEODE) + 1);
            }
        }
    }

    public boolean canConstruct(RobotType robotType) {
        return switch (robotType) {
            case ORE -> ores >= blueprint.getOreRobotCost().getOreCost();
            case CLAY -> ores >= blueprint.getClayRobotCost().getOreCost();
            case OBSIDIAN -> ores >= blueprint.getObsidianRobotCost().getOreCost() && clay >= blueprint.getObsidianRobotCost().getClayCost();
            case GEODE -> ores >= blueprint.getGeodeRobotCost().getOreCost() && obsidian >= blueprint.getGeodeRobotCost().getObsidianCost();
        };
    }
}
