package com.cp.advent2022.data.day11.processor;

import com.cp.advent2022.data.day11.Monkey;

import java.util.List;

public interface MonkeyMapper {
    Monkey createMonkey(List<String> fromLines);
}
