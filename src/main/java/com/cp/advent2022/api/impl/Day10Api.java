package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.component.AdventResourceLoader;
import com.cp.advent2022.data.day10.Statement;
import com.cp.advent2022.data.day10.computer.AbstractHandheldDevice;
import com.cp.advent2022.data.day10.computer.impl.SimpleHandheldDevice;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Command(name = "day10", mixinStandardHelpOptions = true)
public class Day10Api extends DayApi {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public Day10Api(AdventResourceLoader adventResourceLoader) {
        super(10, adventResourceLoader);
    }

    @Override
    protected void execute() throws ExecutionException, InterruptedException {
        Set<Long> firstPartCyclesToCheck = Set.of(20L, 60L, 100L, 140L, 180L, 220L);
        AbstractHandheldDevice simpleHandheldDevice = new SimpleHandheldDevice(firstPartCyclesToCheck);

        for (String line : lines) {
            simpleHandheldDevice.queueStatement(Statement.fromString(line));
        }

        Long sumOfChecked = executor.submit(simpleHandheldDevice).get();

        // ---

        System.out.printf("1. The sum of signal strengths during cycles %s is %d.\n\n", firstPartCyclesToCheck, sumOfChecked);
    }
}
