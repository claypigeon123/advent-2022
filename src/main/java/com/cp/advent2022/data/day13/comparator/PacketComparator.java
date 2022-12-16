package com.cp.advent2022.data.day13.comparator;

import java.util.Comparator;
import java.util.List;

public class PacketComparator implements Comparator<Object> {

    @Override
    public int compare(Object o1, Object o2) {
        return comparePackets(o1, o2).getCompare();
    }

    private CompareResult comparePackets(Object left, Object right) {
        if (left instanceof Integer leftInt && right instanceof Integer rightInt) {
            if (leftInt < rightInt) return CompareResult.RIGHT_ORDER;
            else if (leftInt > rightInt) return CompareResult.NOT_RIGHT_ORDER;
            else return CompareResult.CONTINUE;
        }

        if (left instanceof List<?> leftList && right instanceof List<?> rightList) {
            int min = Math.min(leftList.size(), rightList.size());

            for (int i = 0; i < min; i++) {
                var result = comparePackets(leftList.get(i), rightList.get(i));
                if (result != CompareResult.CONTINUE) return result;
            }

            if (leftList.size() < rightList.size()) return CompareResult.RIGHT_ORDER;
            else if (leftList.size() > rightList.size()) return CompareResult.NOT_RIGHT_ORDER;
            else return CompareResult.CONTINUE;
        }

        if (left instanceof List<?> leftList && right instanceof Integer rightInt) {
            return comparePackets(leftList, List.of(rightInt));
        }

        if (left instanceof Integer leftInt && right instanceof List<?> rightList) {
            return comparePackets(List.of(leftInt), rightList);
        }

        throw new RuntimeException("Invalid path");
    }
}
