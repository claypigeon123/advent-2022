package com.cp.advent2022.data.day4;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Range {
    private int from;
    private int to;

    public boolean coversRange(Range other) {
        return (other.from <= from && other.to >= to) || (from <= other.from && to >= other.to);
    }
}
