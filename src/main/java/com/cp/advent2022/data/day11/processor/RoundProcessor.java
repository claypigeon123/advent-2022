package com.cp.advent2022.data.day11.processor;

import com.cp.advent2022.data.day11.Monkey;

import java.util.List;

public interface RoundProcessor {
    long processRounds(List<Monkey> monkeys, int rounds, Integer quellBy);
}
