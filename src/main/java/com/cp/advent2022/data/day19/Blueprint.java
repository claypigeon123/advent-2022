package com.cp.advent2022.data.day19;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.stream.Stream;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Blueprint {
    private int id;
    private Cost oreRobotCost;
    private Cost clayRobotCost;
    private Cost obsidianRobotCost;
    private Cost geodeRobotCost;

    public static Blueprint fromString(String line) {
        int id;
        Cost oreRobot;
        Cost clayRobot;
        Cost obsidianRobot;
        Cost geodeRobot;

        try (Scanner scan = new Scanner(line)) {
            scan.findInLine("Blueprint (\\d+): Each ore robot costs (\\d+) ore\\. Each clay robot costs (\\d+) ore\\. Each obsidian robot costs (\\d+) ore and (\\d+) clay\\. Each geode robot costs (\\d+) ore and (\\d+) obsidian\\.");

            MatchResult result = scan.match();

            id = Integer.parseInt(result.group(1));
            oreRobot = new Cost(Integer.parseInt(result.group(2)), 0, 0);
            clayRobot = new Cost(Integer.parseInt(result.group(3)), 0, 0);
            obsidianRobot = new Cost(Integer.parseInt(result.group(4)), Integer.parseInt(result.group(5)), 0);
            geodeRobot = new Cost(Integer.parseInt(result.group(6)), 0, Integer.parseInt(result.group(7)));
        }

        return new Blueprint(id, oreRobot, clayRobot, obsidianRobot, geodeRobot);
    }

    public int getHighestCost(OreType oreType) {
        return Stream.of(oreRobotCost, clayRobotCost, obsidianRobotCost, geodeRobotCost)
            .mapToInt(c -> switch (oreType) {
                case ORE -> c.getOreCost();
                case CLAY -> c.getClayCost();
                case OBSIDIAN -> c.getObsidianCost();
                default -> throw new IllegalArgumentException();
            })
            .max()
            .orElseThrow();
    }
}
