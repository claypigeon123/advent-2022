package com.cp.advent2022.data.day17;

import com.cp.advent2022.data.common.Position2D;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class Rock {
    private static int counter = -1;

    private List<Position2D> parts;
    private boolean settled;

    public static Rock next(long leftEdge, long bottomEdge) {
        counter++;
        if (counter == 5) counter = 0;

        return switch (counter) {
            case 0 -> horizontalLine(leftEdge, bottomEdge);
            case 1 -> plusSign(leftEdge, bottomEdge);
            case 2 -> invertedL(leftEdge, bottomEdge);
            case 3 -> verticalLine(leftEdge, bottomEdge);
            case 4 -> square(leftEdge, bottomEdge);
            default -> throw new RuntimeException();
        };
    }

    private static Rock horizontalLine(long leftEdge, long bottomEdge) {
        List<Position2D> parts = new ArrayList<>();
        for (long x = leftEdge; x <= leftEdge + 3; x++) {
            parts.add(new Position2D(bottomEdge, x));
        }

        return new Rock(Collections.unmodifiableList(parts), false);
    }

    private static Rock plusSign(long leftEdge, long bottomEdge) {
        Position2D up = new Position2D(bottomEdge - 2, leftEdge + 1);
        Position2D left = new Position2D(bottomEdge - 1, leftEdge);
        Position2D center = new Position2D(bottomEdge - 1, leftEdge + 1);
        Position2D right = new Position2D(bottomEdge - 1, leftEdge + 2);
        Position2D bottom = new Position2D(bottomEdge, leftEdge + 1);

        return new Rock(List.of(up, left, center, right, bottom), false);
    }

    private static Rock invertedL(long leftEdge, long bottomEdge) {
        Position2D top = new Position2D(bottomEdge - 2, leftEdge + 2);
        Position2D center = new Position2D(bottomEdge - 1, leftEdge + 2);
        Position2D lineRight = new Position2D(bottomEdge, leftEdge + 2);
        Position2D lineCenter = new Position2D(bottomEdge, leftEdge + 1);
        Position2D lineLeft = new Position2D(bottomEdge, leftEdge);

        return new Rock(List.of(top, center, lineRight, lineCenter, lineLeft), false);
    }

    private static Rock verticalLine(long leftEdge, long bottomEdge) {
        List<Position2D> parts = new ArrayList<>();
        for (long y = bottomEdge - 3; y <= bottomEdge; y++) {
            parts.add(new Position2D(y, leftEdge));
        }

        return new Rock(Collections.unmodifiableList(parts), false);
    }

    private static Rock square(long leftEdge, long bottomEdge) {
        Position2D upperLeft = new Position2D(bottomEdge - 1, leftEdge);
        Position2D upperRight = new Position2D(bottomEdge - 1, leftEdge + 1);
        Position2D lowerLeft = new Position2D(bottomEdge, leftEdge);
        Position2D lowerRight = new Position2D(bottomEdge, leftEdge + 1);

        return new Rock(List.of(upperLeft, upperRight, lowerLeft, lowerRight), false);
    }
}
