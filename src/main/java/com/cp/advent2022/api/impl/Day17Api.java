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

    @Override
    protected void execute() throws Exception {
        RockTower rockTower = RockTower.fromDraftString(lines.get(0));

        while (rockTower.getSettledN() < 2022) {
            rockTower.spawnRock();
        }

        // ---

        System.out.printf(
            "1. The tower is %d units tall after 2022 rocks have settled.\n\n",
            (RockTower.FLOOR_Y - rockTower.getTop() - 1)
        );
    }
}
