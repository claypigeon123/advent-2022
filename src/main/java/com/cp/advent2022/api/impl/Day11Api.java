package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.aspect.resloader.LoadAdventResource;
import com.cp.advent2022.data.day11.Monkey;
import com.cp.advent2022.data.day11.processor.MonkeyMapper;
import com.cp.advent2022.data.day11.processor.RoundProcessor;
import com.cp.advent2022.data.day11.processor.impl.SimpleMonkeyMapper;
import com.cp.advent2022.data.day11.processor.impl.SimpleRoundProcessor;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.ArrayList;
import java.util.List;

@Component
@LoadAdventResource(11)
@Command(name = "day11", mixinStandardHelpOptions = true)
public class Day11Api extends DayApi {

    private final MonkeyMapper simpleMonkeyMapper;

    private final RoundProcessor roundProcessor;

    public Day11Api() {
        this.simpleMonkeyMapper = new SimpleMonkeyMapper();
        this.roundProcessor = new SimpleRoundProcessor();
    }

    @Override
    protected void execute() throws Exception {
        List<Monkey> monkeysPartOne = new ArrayList<>();
        List<Monkey> monkeysPartTwo = new ArrayList<>();

        List<String> rollingLines = new ArrayList<>();
        for (String line : lines) {
            if (line.isBlank()) {
                monkeysPartOne.add(simpleMonkeyMapper.createMonkey(rollingLines));
                monkeysPartTwo.add(simpleMonkeyMapper.createMonkey(rollingLines));
                rollingLines = new ArrayList<>();
                continue;
            }

            rollingLines.add(line);
        }
        monkeysPartOne.add(simpleMonkeyMapper.createMonkey(rollingLines));
        monkeysPartTwo.add(simpleMonkeyMapper.createMonkey(rollingLines));

        long monkeyBusinessPartOne = roundProcessor.processRounds(monkeysPartOne, 20, 3);

        long monkeyBusinessPartTwo = roundProcessor.processRounds(monkeysPartTwo, 10_000, null);

        // --

        System.out.printf("1. The level of monkey business after 20 rounds is %d.\n\n", monkeyBusinessPartOne);

        System.out.printf("2. The level of monkey business after 10000 rounds is %d.\n\n", monkeyBusinessPartTwo);
    }
}
