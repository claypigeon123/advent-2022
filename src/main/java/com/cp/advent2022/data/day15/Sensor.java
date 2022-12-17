package com.cp.advent2022.data.day15;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Sensor extends GridItem {

    private int coversDistance;

    public Sensor(int y, int x, int coversDistance) {
        super(y, x);
        this.coversDistance = coversDistance;
    }
}
