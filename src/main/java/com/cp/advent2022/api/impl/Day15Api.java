package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.aspect.resloader.LoadAdventResource;
import com.cp.advent2022.data.common.PuzzleUtils;
import com.cp.advent2022.data.day15.Sensor;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.ArrayList;
import java.util.List;

@Component
@LoadAdventResource(15)
@Command(name = "day15", mixinStandardHelpOptions = true)
public class Day15Api extends DayApi {

    private static final int PART_ONE_ROW = 2000000;

    @Override
    protected void execute() throws Exception {
        List<Sensor> sensors = new ArrayList<>();

        for (String line : lines) {
            int x = Integer.parseInt(line.substring(
                line.indexOf("x=") + 2,
                line.indexOf(",")
            ));
            int y = Integer.parseInt(line.substring(
                line.indexOf("y=") + 2,
                line.indexOf(":")
            ));
            int reachX = Integer.parseInt(line.substring(
                line.lastIndexOf("x=") + 2,
                line.lastIndexOf(",")
            ));
            int reachY = Integer.parseInt(line.substring(
                line.lastIndexOf("y=") + 2
            ));

            sensors.add(new Sensor(y, x, PuzzleUtils.manhattanDistance(y, x, reachY, reachX)));
        }

        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        for (Sensor sensor : sensors) {
            int range = sensor.getCoversDistance() - Math.abs(sensor.getY() - PART_ONE_ROW);
            minX = Math.min(sensor.getX() - range, minX);
            maxX = Math.max(sensor.getX() + range, maxX);
        }
        int count = maxX - minX;

        // ---

        System.out.printf("1. In row %d, the number of positions that cannot contain a beacon is %d.\n\n", PART_ONE_ROW, count);
    }
}
