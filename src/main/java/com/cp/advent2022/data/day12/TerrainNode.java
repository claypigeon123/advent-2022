package com.cp.advent2022.data.day12;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class TerrainNode {
    private int y;
    private int x;
    private int depth;
    private char elevation;
    private Set<String> visited;
}
