package com.cp.advent2022.data.day2.resolver.impl;

import com.cp.advent2022.data.day2.GuessedMatchRound;
import com.cp.advent2022.data.day2.Hand;
import com.cp.advent2022.data.day2.Outcome;
import com.cp.advent2022.data.day2.resolver.MatchRoundResolver;
import lombok.NonNull;

public class GuessedMatchRoundResolver implements MatchRoundResolver<GuessedMatchRound> {

    @Override
    public int resolveRound(@NonNull GuessedMatchRound round) {
        int score = 0;

        switch (round.getIPlay()) {
            case ROCK -> score += 1;
            case PAPER -> score += 2;
            case SCISSORS -> score += 3;
        }

        switch (resolveHands(round.getOpponentPlays(), round.getIPlay())) {
            case WON -> score += 6;
            case DRAW -> score += 3;
        }

        return score;
    }

    private Outcome resolveHands(Hand opponent, Hand me) {
        if (opponent == me) {
            return Outcome.DRAW;
        }

        return switch (opponent) {
            case ROCK -> me == Hand.PAPER ? Outcome.WON : Outcome.LOST;
            case PAPER -> me == Hand.SCISSORS ? Outcome.WON : Outcome.LOST;
            case SCISSORS -> me == Hand.ROCK ? Outcome.WON : Outcome.LOST;
        };
    }
}
