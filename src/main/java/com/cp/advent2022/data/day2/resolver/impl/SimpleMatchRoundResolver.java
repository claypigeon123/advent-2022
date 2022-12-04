package com.cp.advent2022.data.day2.resolver.impl;

import com.cp.advent2022.data.day2.MatchRound;
import com.cp.advent2022.data.day2.resolver.MatchRoundResolver;
import lombok.NonNull;

public class SimpleMatchRoundResolver implements MatchRoundResolver {

    @Override
    public int resolveRound(@NonNull MatchRound matchRound) {
        int score = 0;

        switch (matchRound.getIPlay()) {
            case ROCK -> score += 1;
            case PAPER -> score += 2;
            case SCISSORS -> score += 3;
        }

        switch (resolveHands(matchRound.getOpponentPlays(), matchRound.getIPlay())) {
            case WON -> score += 6;
            case DRAW -> score += 3;
        }

        return score;
    }
}
