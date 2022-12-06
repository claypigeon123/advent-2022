package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.component.AdventResourceLoader;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.regex.Pattern;

@Component
@Command(name = "day6", mixinStandardHelpOptions = true)
public class Day6Api extends DayApi {

    private static final String ALL_CHARS_UNIQUE_REGEX = Pattern.compile("^(?:([A-Za-z])(?!.*\\1))*$").pattern();

    public Day6Api(AdventResourceLoader adventResourceLoader) {
        super(6, adventResourceLoader);
    }

    @Override
    protected void execute() {
        String dataStreamBuffer = lines.get(0);

        int startOfPacketMarker = 0;
        for (int i = 3; i < dataStreamBuffer.length(); i++) {
            String sub = dataStreamBuffer.substring(i - 3, i + 1);

            if (sub.matches(ALL_CHARS_UNIQUE_REGEX)) {
                startOfPacketMarker = i + 1;
                break;
            }
        }

        // ---

        System.out.printf("1. The first time a marker appears is after %d characters have been processed.\n\n", startOfPacketMarker);
    }
}
