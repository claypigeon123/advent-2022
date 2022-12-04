package com.cp.advent2022.data.day3;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Item {
    private Character itemType;
    private int priority;

    public static Item fromChar(Character c) {
        int priority = Character.isLowerCase(c) ? ((int) c) - 96 : ((int) c) - 38;

        return new Item(c, priority);
    }
}
