package com.cp.advent2022.data.day13.comparator;

public enum CompareResult {
    RIGHT_ORDER(1),
    NOT_RIGHT_ORDER(-1),
    CONTINUE(0);

    private final int compare;

    CompareResult(int compare) {
        this.compare = compare;
    }

    public int getCompare() {
        return compare;
    }
}
