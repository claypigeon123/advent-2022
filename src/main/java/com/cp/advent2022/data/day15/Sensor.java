package com.cp.advent2022.data.day15;

import com.cp.advent2022.data.common.PuzzleUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Sensor extends GridItem {

    private int coversDistance;

    public Sensor(int y, int x, int coversDistance) {
        super(y, x);
        this.coversDistance = coversDistance;
    }

    public boolean inRange(int y, int x) {
        return PuzzleUtils.manhattanDistance(this.y, this.x, y, x) <= coversDistance;
    }

    public List<GridItem> edges() {
        List<GridItem> edges = new ArrayList<>();

        for (int x = this.x - coversDistance - 1; x <= this.x + coversDistance + 1; x++) {
            int delta = x <= this.x
                ? Math.abs((this.x - coversDistance - 1) - x)
                : Math.abs((this.x + coversDistance + 1) - x);

            if (delta == 0) {
                edges.add(new GridItem(this.y, x));
                continue;
            }

            edges.add(new GridItem(this.y - delta, x));
            edges.add(new GridItem(this.y + delta, x));
        }

        return edges;
    }
}
