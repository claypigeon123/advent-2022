package com.cp.advent2022.data.day2.resolver.impl;

import com.cp.advent2022.data.day2.ActualMatchRound;
import com.cp.advent2022.data.day2.Hand;
import com.cp.advent2022.data.day2.resolver.MatchRoundResolver;
import lombok.NonNull;

public class ActualMatchRoundResolver implements MatchRoundResolver<ActualMatchRound> {

    @Override
    public int resolveRound(@NonNull ActualMatchRound round) {
        int score = 0;

        Hand iPlay;

        switch (round.getShouldEndIn()) {
            case WON -> {
                iPlay = getOpposingHand(round.getOpponentPlays(), true);
                score += 6;
            }
            case DRAW -> {
                iPlay = round.getOpponentPlays();
                score += 3;
            }
            case LOST -> iPlay = getOpposingHand(round.getOpponentPlays(), false);
            default -> throw new IllegalArgumentException();
        }

        switch (iPlay) {
            case ROCK -> score += 1;
            case PAPER -> score += 2;
            case SCISSORS -> score += 3;
        }

        return score;
    }

    private Hand getOpposingHand(Hand hand, boolean win) {
        return switch (hand) {
            case ROCK -> win ? Hand.PAPER : Hand.SCISSORS;
            case PAPER -> win ? Hand.SCISSORS : Hand.ROCK;
            case SCISSORS -> win ? Hand.ROCK : Hand.PAPER;
        };
    }
}
