package com.cp.advent2022.api.impl;

import com.cp.advent2022.annotation.LoadAdventResource;
import com.cp.advent2022.api.DayApi;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.regex.Pattern;

@Component
@LoadAdventResource
@Command(name = "day6", mixinStandardHelpOptions = true)
public class Day6Api extends DayApi {

    private static final String ALL_CHARS_UNIQUE_REGEX = Pattern.compile("^(?:([A-Za-z])(?!.*\\1))*$").pattern();

    public Day6Api() {
        super(6);
    }

    @Override
    protected void execute() {
        String dataStreamBuffer = lines.get(0);

        int startOfPacketMarker = lookForMarkerStart(dataStreamBuffer, 4);

        int startOfMessageMarker = lookForMarkerStart(dataStreamBuffer, 14);

        // ---

        System.out.printf("1. The first time a start-of-packet marker appears is after %d characters have been processed.\n\n", startOfPacketMarker);

        System.out.printf("2. The first time a start-of-message marker appears is after %d characters have been processed.\n\n", startOfMessageMarker);
    }

    private int lookForMarkerStart(String buffer, int blockSize) {
        int startMarker = 0;
        for (int i = blockSize - 1; i < buffer.length(); i++) {
            String sub = buffer.substring(i - (blockSize - 1), i + 1);

            if (sub.matches(ALL_CHARS_UNIQUE_REGEX)) {
                startMarker = i + 1;
                break;
            }
        }

        return startMarker;
    }
}
