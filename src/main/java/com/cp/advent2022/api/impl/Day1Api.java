package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.component.AdventResourceLoader;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(
    name = "day1",
    mixinStandardHelpOptions = true
)
public class Day1Api extends DayApi {

    public Day1Api(AdventResourceLoader adventResourceLoader) {
        super(1, adventResourceLoader);
    }

    @Override
    protected void execute() {
        // nothing yet
    }
}
