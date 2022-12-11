package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.aspect.resloader.LoadAdventResource;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@LoadAdventResource(11)
@Command(name = "day11", mixinStandardHelpOptions = true)
public class Day11Api extends DayApi {

    @Override
    protected void execute() throws Exception {

    }
}
