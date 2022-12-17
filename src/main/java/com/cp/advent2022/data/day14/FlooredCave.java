package com.cp.advent2022.data.day14;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FlooredCave extends BottomlessCave {
    private int floorY;

    public FlooredCave() {
        this.floorY = 0;
    }

    @Override
    public void applyRockVectorGroup(List<Vector> vectors) {
        super.applyRockVectorGroup(vectors);

        for (Vector v : vectors) {
            int maxY = Math.max(v.getFromY(), v.getToY());
            if ((maxY + 2) > floorY) {
                floorY = maxY + 2;
            }
        }
    }

    public void drawFloor() {
        Arrays.fill(terrain[floorY], ROCK);
    }

    @Override
    public void trickleNewSandUnit() {
        if (terrain[SAND_ORIGIN_Y][SAND_ORIGIN_X] == SAND) {
            filled = true;
            return;
        }

        super.trickleNewSandUnit();
    }
}
