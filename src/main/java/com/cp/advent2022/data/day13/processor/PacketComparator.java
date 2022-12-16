package com.cp.advent2022.data.day13.processor;

public interface PacketComparator {
    CompareResult compare(Object left, Object right);
}
