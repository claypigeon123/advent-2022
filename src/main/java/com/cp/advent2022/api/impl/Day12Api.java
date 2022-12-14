package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.aspect.resloader.LoadAdventResource;
import com.cp.advent2022.data.day12.TerrainNode;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@LoadAdventResource(12)
@Command(name = "day12", mixinStandardHelpOptions = true)
public class Day12Api extends DayApi {
    private static final char STARTING_POINT = 'S';
    private static final char TARGET = 'E';

    @Override
    protected void execute() throws Exception {
        List<List<Character>> terrainMap = new ArrayList<>();
        TerrainNode root = new TerrainNode(0, 0, 0, '0', new HashSet<>());
        AtomicInteger targetY = new AtomicInteger(0), targetX = new AtomicInteger(0);

        for (int y = 0; y < lines.size(); y++) {
            List<Character> row = new ArrayList<>();
            String line = lines.get(y);

            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                if (c == STARTING_POINT) {
                    root.setY(y);
                    root.setX(x);
                    root.setElevation('a');
                    root.getVisited().add(positionToString(y, x));
                    row.add('a');
                } else if (c == TARGET) {
                    targetY.set(y);
                    targetX.set(x);
                    row.add('z');
                } else {
                    row.add(c);
                }
            }

            terrainMap.add(row);
        }

        int stepsPartOne = findFewestStepsRequiredFromPosition(terrainMap, root, targetY.get(), targetX.get());

        List<TerrainNode> startNodes = new ArrayList<>();
        for (int y = 0; y < terrainMap.size(); y++) {
            for (int x = 0; x < terrainMap.get(y).size(); x++) {
                if (terrainMap.get(y).get(x) == 'a') {
                    startNodes.add(new TerrainNode(y, x, 0, 'a', new HashSet<>()));
                }
            }
        }

        int stepsPartTwo = startNodes.parallelStream()
            .map(terrainNode -> findFewestStepsRequiredFromPosition(terrainMap, terrainNode, targetY.get(), targetX.get()))
            .filter(Objects::nonNull)
            .mapToInt(i -> i)
            .min()
            .orElseThrow();

        // --

        System.out.printf("1. The fewest possible steps to reach the destination is %d.\n\n", stepsPartOne);

        System.out.printf("2. The fewest possible steps to reach the destination is %d.\n\n", stepsPartTwo);
    }

    private Integer findFewestStepsRequiredFromPosition(List<List<Character>> terrainMap, TerrainNode root, int targetY, int targetX) {
        Integer stepsRequired = null;
        Queue<TerrainNode> traversalQueue = new LinkedList<>();
        traversalQueue.offer(root);

        while (traversalQueue.peek() != null) {
            TerrainNode node = traversalQueue.poll();
            int nextDepth = node.getDepth() + 1;

            if (node.getY() == targetY && node.getX() == targetX) {
                stepsRequired = node.getDepth();
                break;
            }

            if (node.getY() != 0) {
                traverseVertical(terrainMap, traversalQueue, node, nextDepth, node.getY() - 1);
            }

            if (node.getY() != terrainMap.size() - 1) {
                traverseVertical(terrainMap, traversalQueue, node, nextDepth, node.getY() + 1);
            }

            if (node.getX() != 0) {
                traverseHorizontal(terrainMap, traversalQueue, node, nextDepth, node.getX() - 1);
            }

            if (node.getX() != terrainMap.get(node.getY()).size() - 1) {
                traverseHorizontal(terrainMap, traversalQueue, node, nextDepth, node.getX() + 1);
            }
        }

        return stepsRequired;
    }

    private void traverseVertical(List<List<Character>> terrainMap, Queue<TerrainNode> traversalQueue, TerrainNode node, int nextDepth, int nextY) {
        char target = terrainMap.get(nextY).get(node.getX());
        if (target - node.getElevation() <= 1 && !node.getVisited().contains(positionToString(nextY, node.getX()))) {
            node.getVisited().add(positionToString(nextY, node.getX()));
            traversalQueue.offer(new TerrainNode(nextY, node.getX(), nextDepth, target, node.getVisited()));
        }
    }

    private void traverseHorizontal(List<List<Character>> terrainMap, Queue<TerrainNode> traversalQueue, TerrainNode node, int nextDepth, int nextX) {
        char target = terrainMap.get(node.getY()).get(nextX);
        if (target - node.getElevation() <= 1 && !node.getVisited().contains(positionToString(node.getY(), nextX))) {
            node.getVisited().add(positionToString(node.getY(), nextX));
            traversalQueue.offer(new TerrainNode(node.getY(), nextX, nextDepth, target, node.getVisited()));
        }
    }

    private String positionToString(int y, int x) {
        return String.format("%d---%d", y, x);
    }
}
