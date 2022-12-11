package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.aspect.resloader.LoadAdventResource;
import com.cp.advent2022.data.day10.Statement;
import com.cp.advent2022.data.day10.computer.impl.HandheldDeviceWithDisplay;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@LoadAdventResource
@Command(name = "day10", mixinStandardHelpOptions = true)
public class Day10Api extends DayApi {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public Day10Api() {
        super(10);
    }

    @Override
    protected void execute() throws ExecutionException, InterruptedException {
        Set<Integer> cyclesToCheck = Set.of(20, 60, 100, 140, 180, 220);
        HandheldDeviceWithDisplay handheldDevice = new HandheldDeviceWithDisplay(cyclesToCheck);

        for (String line : lines) {
            handheldDevice.queueStatement(Statement.fromString(line));
        }

        executor.submit(handheldDevice).get();

        // ---

        System.out.printf("1. The sum of signal strengths during cycles %s is %d.\n\n", cyclesToCheck, handheldDevice.getSumOfCheckedCycles());

        System.out.print("2. The screen rendered by the device is:\n");
        handheldDevice.renderScreen(System.out);
    }
}
