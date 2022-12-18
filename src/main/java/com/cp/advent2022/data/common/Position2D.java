package com.cp.advent2022.data.common;

import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Position2D {

    @Getter @Setter
    private long y;

    @Getter @Setter
    private long x;

    @Override
    public String toString() {
        return String.format("%d---%d", y, x);
    }
}
