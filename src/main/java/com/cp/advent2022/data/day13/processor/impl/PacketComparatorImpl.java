package com.cp.advent2022.data.day13.processor.impl;

import com.cp.advent2022.data.day13.processor.CompareResult;
import com.cp.advent2022.data.day13.processor.PacketComparator;

import java.util.List;

public class PacketComparatorImpl implements PacketComparator {

    @Override
    public CompareResult compare(Object left, Object right) {
        if (left instanceof Integer leftInt && right instanceof Integer rightInt) {
            if (leftInt < rightInt) return CompareResult.RIGHT_ORDER;
            else if (leftInt > rightInt) return CompareResult.NOT_RIGHT_ORDER;
            else return CompareResult.CONTINUE;
        }

        if (left instanceof List<?> leftList && right instanceof List<?> rightList) {
            int min = Math.min(leftList.size(), rightList.size());

            for (int i = 0; i < min; i++) {
                var result = compare(leftList.get(i), rightList.get(i));
                if (result != CompareResult.CONTINUE) return result;
            }

            if (leftList.size() < rightList.size()) return CompareResult.RIGHT_ORDER;
            else if (leftList.size() > rightList.size()) return CompareResult.NOT_RIGHT_ORDER;
            else return CompareResult.CONTINUE;
        }

        if (left instanceof List<?> leftList && right instanceof Integer rightInt) {
            return compare(leftList, List.of(rightInt));
        }

        if (left instanceof Integer leftInt && right instanceof List<?> rightList) {
            return compare(List.of(leftInt), rightList);
        }

        throw new RuntimeException("Invalid path");
    }
}
