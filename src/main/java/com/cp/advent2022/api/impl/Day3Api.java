package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.component.AdventResourceLoader;
import com.cp.advent2022.data.day3.Rucksack;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.*;

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
        List<Rucksack> rollingRucksacks = new ArrayList<>();
        int prioritySum = 0;
        int groupedPrioritySum = 0;

        for (String line : lines) {
            Rucksack rucksack = Rucksack.fromString(line);

            prioritySum += findCommonPriorityInRucksack(rucksack);

            rollingRucksacks.add(rucksack);

            if (rollingRucksacks.size() == 3) {
                groupedPrioritySum += findCommonPriorityInRucksacks(rollingRucksacks);
                rollingRucksacks = new ArrayList<>();
            }
        }

        // ---

        System.out.printf("1. The sum of the misplaced item types' priority is %d.\n\n", prioritySum);

        System.out.printf("2. The sum of each three-grouped elf groups' priorities is %d.\n\n", groupedPrioritySum);
    }

    private int calculatePriority(Character c) {
        return Character.isLowerCase(c) ? ((int) c) - 96 : ((int) c) - 38;
    }

    private int findCommonPriorityInRucksack(Rucksack rucksack) {
        Set<Character> comp1 = new HashSet<>(rucksack.getCompartmentOne());
        Set<Character> comp2 = new HashSet<>(rucksack.getCompartmentTwo());

        comp1.retainAll(comp2);

        return comp1.stream()
            .findAny()
            .map(this::calculatePriority)
            .orElse(0);
    }

    private int findCommonPriorityInRucksacks(List<Rucksack> rucksacks) {
        if (rucksacks.size() < 2) {
            return 0;
        }

        List<Set<Character>> rucksacksContents = new ArrayList<>();

        for (Rucksack rucksack : rucksacks) {
            Set<Character> chars = new HashSet<>();
            chars.addAll(rucksack.getCompartmentOne());
            chars.addAll(rucksack.getCompartmentTwo());
            rucksacksContents.add(chars);
        }

        Set<Character> baseSet = rucksacksContents.get(0);

        for (int i = 1; i < rucksacksContents.size(); i++) {
            baseSet.retainAll(rucksacksContents.get(i));
        }

        return baseSet.stream()
            .findAny()
            .map(this::calculatePriority)
            .orElse(0);
    }
}
