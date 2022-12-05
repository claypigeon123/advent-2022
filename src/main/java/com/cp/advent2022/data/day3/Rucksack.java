package com.cp.advent2022.data.day3;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Rucksack {
    private List<Character> compartmentOne;

    private List<Character> compartmentTwo;

    public static Rucksack fromString(String items) {
        List<Character> compartmentOne = new ArrayList<>();
        List<Character> compartmentTwo = new ArrayList<>();

        char[] chars = items.toCharArray();
        int half = chars.length / 2;

        for (int i = 0; i < chars.length; i++) {
            if (i < half) {
                compartmentOne.add(chars[i]);
            } else {
                compartmentTwo.add(chars[i]);
            }
        }

        return new Rucksack(compartmentOne, compartmentTwo);
    }
}
