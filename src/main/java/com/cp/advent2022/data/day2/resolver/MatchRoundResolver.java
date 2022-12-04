package com.cp.advent2022.data.day2.resolver;

import com.cp.advent2022.data.day2.Hand;
import com.cp.advent2022.data.day2.MatchRound;
import com.cp.advent2022.data.day2.Outcome;
import lombok.NonNull;

public interface MatchRoundResolver {
    int resolveRound(@NonNull MatchRound matchRound);

    default Outcome resolveHands(Hand opponent, Hand me) {
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
