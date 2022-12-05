package com.cp.advent2022.data.day5;

import lombok.Data;

import java.util.*;

@Data
public class CrateStorage {
    private final Map<Integer, Deque<Crate>> stacks;

    private CrateStorage(int nStacks) {
        stacks = new LinkedHashMap<>();

        for (int i = 1; i <= nStacks; i++) {
            stacks.put(i, new ArrayDeque<>());
        }
    }

    public static CrateStorage fromArrangementLines(List<String> arrangementLines) {
        int nStacks = 0;

        for (int i = 1; i < arrangementLines.get(arrangementLines.size() - 1).length(); i += 4) {
            nStacks++;
        }

        CrateStorage crateStorage = new CrateStorage(nStacks);

        for (int i = arrangementLines.size() - 2; i >= 0; i--) {
            int stackIndex = 1;

            for (int spaceIndex = 1; spaceIndex < arrangementLines.get(i).length(); spaceIndex += 4) {
                char c = arrangementLines.get(i).charAt(spaceIndex);
                if (c != ' ') {
                    crateStorage.stacks.get(stackIndex).push(new Crate(c));
                }
                stackIndex++;
            }
        }

        return crateStorage;
    }
}
