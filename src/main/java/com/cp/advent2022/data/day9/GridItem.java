package com.cp.advent2022.data.day9;

import lombok.Data;

@Data
public class GridItem {
    private int x;
    private int y;

    public GridItem() {
        x = 0;
        y = 0;
    }

    public String positionToString() {
        return String.format("%d---%d", x, y);
    }
}
