package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.aspect.resloader.LoadAdventResource;
import com.cp.advent2022.data.day17.RockTower;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@LoadAdventResource(17)
@Command(name = "day17", mixinStandardHelpOptions = true)
public class Day17Api extends DayApi {

    private static final long PART_ONE_CHECKPOINT = 2022L;
    private static final long PART_TWO_CHECKPOINT = 1_000_000_000_000L;

    @Override
    protected void execute() throws Exception {
        RockTower rockTower = RockTower.fromDraftString(lines.get(0));

        while (rockTower.getSettledN() < PART_ONE_CHECKPOINT) {
            rockTower.spawnRock();
        }
        long heightPartOne = RockTower.FLOOR_Y - rockTower.getTop() - 1;

        while (rockTower.getSettledN() < 10000) {
            rockTower.spawnRock();
        }
        long heightPartTwo = rockTower.findCycleAndSimulateTo(PART_TWO_CHECKPOINT, 1000) - 1;

        // ---

        System.out.printf("1. The tower is %d units tall after %d rocks have settled.\n\n", heightPartOne, PART_ONE_CHECKPOINT);

        System.out.printf("1. The tower is %d units tall after %d rocks have settled.\n\n", heightPartTwo, PART_TWO_CHECKPOINT);
    }
}
