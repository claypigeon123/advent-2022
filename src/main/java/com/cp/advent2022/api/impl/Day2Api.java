package com.cp.advent2022.api.impl;

import com.cp.advent2022.annotation.LoadAdventResource;
import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.data.day2.ActualMatchRound;
import com.cp.advent2022.data.day2.GuessedMatchRound;
import com.cp.advent2022.data.day2.resolver.MatchRoundResolver;
import com.cp.advent2022.data.day2.resolver.impl.ActualMatchRoundResolver;
import com.cp.advent2022.data.day2.resolver.impl.GuessedMatchRoundResolver;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@LoadAdventResource
@Command(name = "day2", mixinStandardHelpOptions = true)
public class Day2Api extends DayApi {

    private final MatchRoundResolver<GuessedMatchRound> guessedResolver;
    private final MatchRoundResolver<ActualMatchRound> actualResolver;

    public Day2Api() {
        super(2);
        guessedResolver = new GuessedMatchRoundResolver();
        actualResolver = new ActualMatchRoundResolver();
    }

    @Override
    protected void execute() {
        int myScorePartOne = 0;
        int myScorePartTwo = 0;

        for (String line : lines) {
            String[] tokens = line.split(" ");

            GuessedMatchRound guessedRound = GuessedMatchRound.fromStringTokens(tokens);
            ActualMatchRound actualRound = ActualMatchRound.fromStringTokens(tokens);

            myScorePartOne += guessedResolver.resolveRound(guessedRound);
            myScorePartTwo += actualResolver.resolveRound(actualRound);
        }

        // ---

        System.out.printf("1. My total score with the guessed strategy is %d.\n\n", myScorePartOne);

        System.out.printf("2. My total score with the actual strategy is %d.\n\n", myScorePartTwo);
    }
}
