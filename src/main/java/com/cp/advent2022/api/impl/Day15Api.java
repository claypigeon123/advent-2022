package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.aspect.resloader.LoadAdventResource;
import com.cp.advent2022.data.common.PuzzleUtils;
import com.cp.advent2022.data.day15.GridItem;
import com.cp.advent2022.data.day15.Sensor;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.ArrayList;
import java.util.List;

@Component
@LoadAdventResource(15)
@Command(name = "day15", mixinStandardHelpOptions = true)
public class Day15Api extends DayApi {

    private static final int PART_ONE_ROW = 2_000_000;

    private static final int PART_TWO_MIN = 0;
    private static final int PART_TWO_MAX = 4_000_000;

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

        // --- PART 1

        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        for (Sensor sensor : sensors) {
            int range = sensor.getCoversDistance() - Math.abs(sensor.getY() - PART_ONE_ROW);
            minX = Math.min(sensor.getX() - range, minX);
            maxX = Math.max(sensor.getX() + range, maxX);
        }
        int cannotHaveBeaconCount = maxX - minX;

        // --- PART 2

        GridItem distressBeacon = null;
        for (Sensor sensor : sensors) {
            List<GridItem> edges = sensor.edges();

            for (GridItem e : edges) {
                if (e.getY() < PART_TWO_MIN || e.getY() > PART_TWO_MAX || e.getX() < PART_TWO_MIN || e.getX() > PART_TWO_MAX) {
                    continue;
                }

                boolean missedByAllSensors = sensors.stream()
                    .noneMatch(s -> s.inRange(e.getY(), e.getX()));

                if (missedByAllSensors) {
                    distressBeacon = e;
                    break;
                }
            }

            if (distressBeacon != null) break;
        }
        assert distressBeacon != null;

        // ---

        System.out.printf("1. In row %d, the number of positions that cannot contain a beacon is %d.\n\n", PART_ONE_ROW, cannotHaveBeaconCount);

        System.out.printf("2. The tuning frequency of the distress beacon is %d.\n\n", ((long) distressBeacon.getX() * 4_000_000L + (long) distressBeacon.getY()));
    }
}
