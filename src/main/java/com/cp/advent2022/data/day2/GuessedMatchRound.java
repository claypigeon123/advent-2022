package com.cp.advent2022.data.day2;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GuessedMatchRound {

    @NonNull
    private Hand opponentPlays;

    @NonNull
    private Hand iPlay;

    public static GuessedMatchRound fromStringTokens(String[] tokens) {
        Hand opponentPlays = Hand.fromLiteral(tokens[0]);
        Hand iPlay = Hand.fromLiteral(tokens[1]);

        return new GuessedMatchRound(opponentPlays, iPlay);
    }
}
