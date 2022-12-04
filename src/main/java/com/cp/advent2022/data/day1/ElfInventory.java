package com.cp.advent2022.data.day1;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ElfInventory {
    private List<Integer> itemCalories;

    public ElfInventory() {
        itemCalories = new ArrayList<>();
    }

    public void addItem(int calorieCount) {
        itemCalories.add(calorieCount);
    }

    public int getInventoryCalorieCount() {
        return itemCalories.stream()
            .mapToInt(v -> v)
            .sum();
    }
}
