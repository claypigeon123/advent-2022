package com.cp.advent2022.data.day3;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Rucksack {
    private List<Item> compartmentOne;

    private List<Item> compartmentTwo;

    public static Rucksack fromString(String items) {
        List<Item> compartmentOne = new ArrayList<>();
        List<Item> compartmentTwo = new ArrayList<>();

        char[] chars = items.toCharArray();
        int half = chars.length / 2;

        for (int i = 0; i < chars.length; i++) {
            if (i < half) {
                compartmentOne.add(Item.fromChar(chars[i]));
            } else {
                compartmentTwo.add(Item.fromChar(chars[i]));
            }
        }

        return new Rucksack(compartmentOne, compartmentTwo);
    }
}
