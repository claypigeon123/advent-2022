package com.cp.advent2022.data.day17;

import com.cp.advent2022.data.common.Position2D;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RockTower {
    public static final int FLOOR_Y = 32_768;
    private static final int WALL_LEFT_X = 0;
    private static final int WALL_RIGHT_X = 8;

    private Queue<Direction> draft;
    private Set<Position2D> settledPositions;
    private int settledN;
    private int top;

    public static RockTower fromDraftString(String draftStr) {
        Queue<Direction> directions = new LinkedList<>();

        for (String c : draftStr.chars().mapToObj(c -> (char)c).map(String::valueOf).toList()) {
            directions.offer(Direction.fromLiteral(c));
        }

        return new RockTower(directions, new HashSet<>(), 0, FLOOR_Y - 1);
    }

    public void spawnRock() {
        Rock rock = Rock.next(WALL_LEFT_X + 3, top - 3);

        while (!rock.isSettled()) {
            Direction nextDraft = pollNextDraft();

            if (canMoveInDirection(rock, nextDraft)) {
                moveInDirection(rock, nextDraft);
            }

            if (canMoveInDirection(rock, Direction.DOWN)) {
                moveInDirection(rock, Direction.DOWN);
            } else {
                rock.setSettled(true);
            }
        }

        settledPositions.addAll(rock.getParts());
        settledN++;
        top = settledPositions.stream()
            .mapToInt(Position2D::getY)
            .min()
            .orElseThrow() - 1;
    }

    private void moveInDirection(Rock rock, Direction dir) {
        switch (dir) {
            case DOWN -> {
                for (Position2D pos : rock.getParts()) {
                    pos.setY(pos.getY() + 1);
                }
            }
            case RIGHT -> {
                for (Position2D pos : rock.getParts()) {
                    pos.setX(pos.getX() + 1);
                }
            }
            case LEFT -> {
                for (Position2D pos : rock.getParts()) {
                    pos.setX(pos.getX() - 1);
                }
            }
        }
    }

    public boolean canMoveInDirection(Rock rock, Direction dir) {
        List<Position2D> nextPositions = rock.getParts().stream()
            .map(p -> new Position2D(p.getY(), p.getX()))
            .toList();

        Rock tempRock = new Rock(nextPositions, false);
        moveInDirection(tempRock, dir);

        return tempRock.getParts().stream()
            .allMatch(pos -> !settledPositions.contains(pos)
                && pos.getX() != WALL_LEFT_X
                && pos.getX() != WALL_RIGHT_X
                && pos.getY() != FLOOR_Y
            );
    }

    private Direction pollNextDraft() {
        Direction direction = draft.poll();
        draft.offer(direction);
        return direction;
    }

    public void print() {
        try (PrintWriter pw = new PrintWriter(new BufferedOutputStream(Files.newOutputStream(Path.of("out.txt"))))) {
            Character[][] grid = new Character[FLOOR_Y + 1][WALL_RIGHT_X + 1];
            for (Position2D pos : settledPositions) {
                grid[pos.getY()][pos.getX()] = '#';
            }

            for (int y = 0; y < grid.length; y++) {
                Character[] row = grid[y];
                for (int x = 0; x < row.length; x++) {
                    Character c = row[x];
                    if (c == null) {
                        if (x == 0 || x == WALL_RIGHT_X) {
                            pw.print('|');
                        } else if (y == FLOOR_Y) {
                            pw.print('_');
                        } else pw.print('.');
                    } else {
                        pw.print(c);
                    }
                }
                pw.print("\n");
            }
            pw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
