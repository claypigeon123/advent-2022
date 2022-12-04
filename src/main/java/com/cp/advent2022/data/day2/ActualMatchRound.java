package com.cp.advent2022.data.day2;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ActualMatchRound {

    @NonNull
    private Hand opponentPlays;

    @NonNull
    private Outcome shouldEndIn;

    public static ActualMatchRound fromStringTokens(String[] tokens) {
        Hand opponentPlays = Hand.fromLiteral(tokens[0]);
        Outcome iShould = Outcome.fromLiteral(tokens[1]);

        return new ActualMatchRound(opponentPlays, iShould);
    }
}
