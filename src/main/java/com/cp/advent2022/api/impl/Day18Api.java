package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.aspect.resloader.LoadAdventResource;
import com.cp.advent2022.data.common.Position3D;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

@Component
@LoadAdventResource(18)
@Command(name = "day18", mixinStandardHelpOptions = true)
public class Day18Api extends DayApi {

    @Override
    protected void execute() throws Exception {
        Set<Position3D> cubes = new HashSet<>();

        for (String line : lines) {
            String[] split = line.split(",");
            cubes.add(new Position3D(
                Long.parseLong(split[2]),
                Long.parseLong(split[1]),
                Long.parseLong(split[0])
            ));
        }

        long allExposedSides = 0;
        for (Position3D checked : cubes) {
            allExposedSides += getExposedSides(checked, cubes);
        }

        Set<Position3D> surfacePositions = floodFill(cubes);

        long outwardExposedSides = 0;
        for (Position3D checked : cubes) {
            outwardExposedSides += getExposedSidesToNeighborsFrom(checked, surfacePositions);
        }

        // ---

        System.out.printf("1. The total number of exposed sides is %d.\n\n", allExposedSides);

        System.out.printf("2. The total number of outward-facing exposed sides is %d.\n\n", outwardExposedSides);
    }

    public int getExposedSides(Position3D location, Set<Position3D> takenPositions) {
        int exposedSides = 6;
        long z = location.getZ(), y = location.getY(), x = location.getX();

        if (takenPositions.contains(new Position3D(z - 1, y, x))) exposedSides--;
        if (takenPositions.contains(new Position3D(z + 1, y, x))) exposedSides--;
        if (takenPositions.contains(new Position3D(z, y - 1, x))) exposedSides--;
        if (takenPositions.contains(new Position3D(z, y + 1, x))) exposedSides--;
        if (takenPositions.contains(new Position3D(z , y, x - 1))) exposedSides--;
        if (takenPositions.contains(new Position3D(z, y, x + 1))) exposedSides--;

        return exposedSides;
    }

    public int getExposedSidesToNeighborsFrom(Position3D location, Set<Position3D> water) {
        int exposedSides = 0;
        long z = location.getZ(), y = location.getY(), x = location.getX();

        if (water.contains(new Position3D(z - 1, y, x))) exposedSides++;
        if (water.contains(new Position3D(z + 1, y, x))) exposedSides++;
        if (water.contains(new Position3D(z, y - 1, x))) exposedSides++;
        if (water.contains(new Position3D(z, y + 1, x))) exposedSides++;
        if (water.contains(new Position3D(z , y, x - 1))) exposedSides++;
        if (water.contains(new Position3D(z, y, x + 1))) exposedSides++;

        return exposedSides;
    }

    private Set<Position3D> floodFill(Set<Position3D> positions) {
        Set<Position3D> lavaSurface = new HashSet<>();
        long minZ = positions.stream().mapToLong(Position3D::getZ).min().orElseThrow() - 1;
        long maxZ = positions.stream().mapToLong(Position3D::getZ).max().orElseThrow() + 1;
        long minY = positions.stream().mapToLong(Position3D::getY).min().orElseThrow() - 1;
        long maxY = positions.stream().mapToLong(Position3D::getY).max().orElseThrow() + 1;
        long minX = positions.stream().mapToLong(Position3D::getX).min().orElseThrow() - 1;
        long maxX = positions.stream().mapToLong(Position3D::getX).max().orElseThrow() + 1;

        Position3D start = new Position3D(minZ, minY, minX);

        Set<Position3D> checked = new HashSet<>();
        Queue<Position3D> queue = new LinkedList<>();
        queue.offer(start);
        checked.add(start);

        while (queue.peek() != null) {
            Position3D pos = queue.poll();
            checked.add(pos);

            Set<Position3D> edges = pos.edges();

            if (edges.stream().anyMatch(positions::contains)) {
                lavaSurface.add(pos);
            }

            for (Position3D next : edges) {
                if (!checked.contains(next) && !positions.contains(next)
                    && next.getZ() >= minZ && next.getZ() <= maxZ
                    && next.getY() >= minY && next.getY() <= maxY
                    && next.getX() >= minX && next.getX() <= maxX
                ) {
                    checked.add(next);
                    queue.offer(next);
                }
            }
        }

        return lavaSurface;
    }
}
