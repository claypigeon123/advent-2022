package com.cp.advent2022.data.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Position3D extends Position2D {

    @Getter @Setter
    protected long z;

    public Position3D(long z, long y, long x) {
        super(y, x);
        this.z = z;
    }

    public Set<Position3D> edges() {
        Position3D upZ = new Position3D(z - 1, y, x);
        Position3D downZ = new Position3D(z + 1, y, x);
        Position3D upY = new Position3D(z, y - 1, x);
        Position3D downY = new Position3D(z, y + 1, x);
        Position3D upX = new Position3D(z, y, x - 1);
        Position3D downX = new Position3D(z, y, x + 1);

        return Set.of(upZ, downZ, upY, downY, upX, downX);
    }

    @Override
    public String toString() {
        return String.format("%d---%d---%d", z, y, x);
    }
}
