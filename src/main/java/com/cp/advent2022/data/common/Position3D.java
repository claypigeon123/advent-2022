package com.cp.advent2022.data.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Position3D extends Position2D {

    @Getter @Setter
    protected long z;

    public Position3D(long z, long y, long x) {
        super(y, x);
        this.z = z;
    }

    @Override
    public String toString() {
        return String.format("%d---%d---%d", z, y, x);
    }
}
