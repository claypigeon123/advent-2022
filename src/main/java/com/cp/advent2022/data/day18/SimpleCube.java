package com.cp.advent2022.data.day18;

import com.cp.advent2022.data.common.Position3D;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleCube {
    protected static final long SIZE = 1;

    protected Position3D location;

    public static SimpleCube fromString(String str) {
        String[] split = str.split(",");
        Position3D location = new Position3D(
            Long.parseLong(split[2]),
            Long.parseLong(split[1]),
            Long.parseLong(split[0])
        );

        return new SimpleCube(location);
    }

    public int getExposedSides(List<SimpleCube> against) {
        int exposedSides = 6;
        long z = location.getZ(), y = location.getY(), x = location.getX();

        Set<Position3D> takenPositions = against.stream()
            .map(SimpleCube::getLocation)
            .collect(Collectors.toSet());

        if (takenPositions.contains(new Position3D(z - SIZE, y, x))) exposedSides--;
        if (takenPositions.contains(new Position3D(z + SIZE, y, x))) exposedSides--;
        if (takenPositions.contains(new Position3D(z, y - SIZE, x))) exposedSides--;
        if (takenPositions.contains(new Position3D(z, y + SIZE, x))) exposedSides--;
        if (takenPositions.contains(new Position3D(z , y, x - SIZE))) exposedSides--;
        if (takenPositions.contains(new Position3D(z, y, x + SIZE))) exposedSides--;

        return exposedSides;
    }
}
