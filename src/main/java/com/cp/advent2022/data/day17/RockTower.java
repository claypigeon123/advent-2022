package com.cp.advent2022.data.day17;

import com.cp.advent2022.data.common.Position2D;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RockTower {
    public static final long FLOOR_Y = 32_768;
    private static final long WALL_LEFT_X = 0;
    private static final long WALL_RIGHT_X = 8;

    private Queue<Direction> draft;
    private Set<Position2D> settledPositions;
    private List<Long> deltas;
    private long settledN;
    private long top;

    public static RockTower fromDraftString(String draftStr) {
        Queue<Direction> directions = new LinkedList<>();

        for (String c : draftStr.chars().mapToObj(c -> (char)c).map(String::valueOf).toList()) {
            directions.offer(Direction.fromLiteral(c));
        }

        return new RockTower(directions, new HashSet<>(), new ArrayList<>(), 0, FLOOR_Y - 1);
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
        long newTop = settledPositions.stream()
            .mapToLong(Position2D::getY)
            .min()
            .orElseThrow() - 1;

        long delta = Math.abs(top - newTop);
        deltas.add(delta);
        top = newTop;
    }

    public long findCycleAndSimulateTo(long targetSettled, int skip) {
        List<Long> cycle = findRepeatingDeltas(skip);
        assert cycle != null;

        // skip until cycle starts
        int cycleStartsAt = Collections.indexOfSubList(deltas, cycle);
        long settled = cycleStartsAt - 1;
        long height = deltas.stream().limit(cycleStartsAt).mapToLong(l -> l).sum();

        // record N cycles until only one incomplete cycle remains
        long fullCycleDelta = cycle.stream().mapToLong(l -> l).sum();
        long times = (targetSettled - settled) / cycle.size();
        height = height + (fullCycleDelta * times);
        settled = settled + (cycle.size() * times);

        // record the incomplete cycle as well until the desired target settled is reached
        for (int i = 0; i < cycle.size() && settled < targetSettled; i++) {
            height += cycle.get(i);
            settled++;
        }

        return height;
    }

    private List<Long> findRepeatingDeltas(int skip) {
        for (int cycleSize = 1; cycleSize < deltas.size() / 2; cycleSize++) {
            List<Long> cycle = deltas.subList(skip, skip + cycleSize);
            boolean thisCycleRepeats = true;

            for (int i = skip; i < deltas.size(); i += cycleSize) {
                List<Long> checked = deltas.subList(i, Math.min(i + cycleSize, deltas.size()));
                for (int c = 0; c < checked.size(); c++) {
                    if (!cycle.get(c).equals(checked.get(c))) {
                        thisCycleRepeats = false;
                        break;
                    }
                }

                if (!thisCycleRepeats) break;
            }

            if (thisCycleRepeats) return cycle;
        }

        return null;
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
}
