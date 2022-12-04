package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.component.AdventResourceLoader;
import com.cp.advent2022.data.day3.Item;
import com.cp.advent2022.data.day3.Rucksack;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.List;

@Component
@Command(
    name = "day3",
    mixinStandardHelpOptions = true
)
public class Day3Api extends DayApi {

    public Day3Api(AdventResourceLoader adventResourceLoader) {
        super(3, adventResourceLoader);
    }

    @Override
    protected void execute() {
        int prioritySum = 0;

        for (String line : lines) {
            Rucksack rucksack = Rucksack.fromString(line);

            prioritySum += findCommonPriority(rucksack.getCompartmentOne(), rucksack.getCompartmentTwo());
        }

        System.out.printf("1. The sum of the misplaced item types' priority is %d", prioritySum);
    }

    private int findCommonPriority(List<Item> comp1, List<Item> comp2) {
        for (Item a : comp1) {
            for (Item b : comp2) {
                if (a.getItemType() == b.getItemType()) {
                    return a.getPriority();
                }
            }
        }

        return 0;
    }
}
