package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.aspect.resloader.LoadAdventResource;
import com.cp.advent2022.data.day1.ElfInventory;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

@Component
@LoadAdventResource(1)
@Command(name = "day1", mixinStandardHelpOptions = true)
public class Day1Api extends DayApi {

    private static final int QUEUE_MAX_SIZE = 3;

    @Override
    protected void execute() {
        BlockingQueue<ElfInventory> sortedInventoryQueue = new PriorityBlockingQueue<>(QUEUE_MAX_SIZE, Comparator.comparing(ElfInventory::getInventoryCalorieCount));

        ElfInventory tempInventory = new ElfInventory();
        for (String line : lines) {
            if (!line.isBlank()) {
                tempInventory.addItem(Integer.parseInt(line));
                continue;
            }

            sortedInventoryQueue.add(tempInventory);

            if (sortedInventoryQueue.size() > QUEUE_MAX_SIZE) {
                sortedInventoryQueue.poll();
            }

            tempInventory = new ElfInventory();
        }

        // ---

        List<ElfInventory> bestElves = new ArrayList<>();
        sortedInventoryQueue.drainTo(bestElves);

        System.out.printf(
            "1. The elf carrying the most calories has %d calories in their bag.\n\n",
            bestElves.get(bestElves.size() - 1).getInventoryCalorieCount()
        );

        System.out.printf(
            "2. The top three elves are carrying %d calories in total.\n\n",
            bestElves.stream().mapToInt(ElfInventory::getInventoryCalorieCount).sum()
        );
    }
}
