package com.cp.advent2022.data.day17;

public enum Direction {
    LEFT("<"),
    RIGHT(">"),
    DOWN("ˇ"),
    UP("^");

    private final String literal;

    Direction(String literal) {
        this.literal = literal;
    }

    public static Direction fromLiteral(String literal) {
        for (Direction dir : Direction.values()) {
            if (dir.literal.equals(literal)) {
                return dir;
            }
        }

        throw new IllegalArgumentException();
    }
}
