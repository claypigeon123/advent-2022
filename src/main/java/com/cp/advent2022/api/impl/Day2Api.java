package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.component.AdventResourceLoader;
import com.cp.advent2022.data.day2.MatchRound;
import com.cp.advent2022.data.day2.resolver.MatchRoundResolver;
import com.cp.advent2022.data.day2.resolver.impl.SimpleMatchRoundResolver;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(
    name = "day2",
    mixinStandardHelpOptions = true
)
public class Day2Api extends DayApi {

    private final MatchRoundResolver resolver;

    public Day2Api(AdventResourceLoader adventResourceLoader) {
        super(2, adventResourceLoader);
        resolver = new SimpleMatchRoundResolver();
    }

    @Override
    protected void execute() {
        int myScore = 0;

        for (String line : lines) {
            String[] tokens = line.split(" ");
            MatchRound round = MatchRound.fromStringTokens(tokens);

            myScore += resolver.resolveRound(round);
        }

        System.out.printf("1. My total score is %d.\n\n", myScore);
    }
}
