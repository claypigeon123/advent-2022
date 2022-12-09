package com.cp.advent2022.data.day9.processor.impl;

import com.cp.advent2022.data.day9.Direction;
import com.cp.advent2022.data.day9.GridItem;
import com.cp.advent2022.data.day9.Instruction;
import com.cp.advent2022.data.day9.processor.GridKnotInstructionProcessor;

import java.util.HashSet;
import java.util.Set;

public class SimpleGridKnotInstructionProcessor implements GridKnotInstructionProcessor {

    @Override
    public Set<String> process(GridItem head, GridItem tail, Instruction instruction) {
        Set<String> tailPositionsVisited = new HashSet<>();
        tailPositionsVisited.add(tail.positionToString());

        for (int i = 0; i < instruction.getN(); i++) {
            moveItem(head, instruction.getDirection());
            followItem(head, tail);
            tailPositionsVisited.add(tail.positionToString());
        }

        return tailPositionsVisited;
    }

    private void moveItem(GridItem item, Direction direction) {
        int x = item.getX(), y = item.getY();
        switch (direction) {
            case U -> item.setY(y + 1);
            case D -> item.setY(y - 1);
            case R -> item.setX(x + 1);
            case L -> item.setX(x - 1);
            case UR -> {
                item.setX(x + 1);
                item.setY(y + 1);
            }
            case UL -> {
                item.setX(x - 1);
                item.setY(y + 1);
            }
            case DR -> {
                item.setX(x + 1);
                item.setY(y - 1);
            }
            case DL -> {
                item.setX(x - 1);
                item.setY(y - 1);
            }
        }
    }

    private void followItem(GridItem leader, GridItem follower) {
        int leaderX = leader.getX(), leaderY = leader.getY();
        int followerX = follower.getX(), followerY = follower.getY();
        Direction direction;

        if (Math.abs(leaderX - followerX) <= 1 && Math.abs(leaderY - followerY) <= 1) {
            return;
        } else if (leaderX == followerX) {
            if (leaderY > followerY) {
                direction = Direction.U;
            } else {
                direction = Direction.D;
            }
        } else if (leaderY == followerY) {
            if (leaderX > followerX) {
                direction = Direction.R;
            } else {
                direction = Direction.L;
            }
        } else if (leaderY > followerY) {
            if (leaderX > followerX) {
                direction = Direction.UR;
            } else {
                direction = Direction.UL;
            }
        } else {
            if (leaderX > followerX) {
                direction = Direction.DR;
            } else {
                direction = Direction.DL;
            }
        }

        moveItem(follower, direction);
    }
}
