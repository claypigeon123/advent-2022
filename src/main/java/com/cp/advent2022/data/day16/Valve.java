package com.cp.advent2022.data.day16;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Valve {
    private String label;
    private int flowRate;
    private List<String> leadsTo;

    public static Valve fromString(String line) {
        String label = line.substring(line.indexOf("Valve ") + 6, 8);

        int flowRate = Integer.parseInt(line.substring(
            line.indexOf("rate=") + 5,
            line.indexOf(";")
        ));

        List<String> leadsTo = Arrays.stream(line.substring(line.lastIndexOf("valve") + 6).split(", "))
            .map(String::trim)
            .toList();

        return new Valve(label, flowRate, leadsTo);
    }
}
