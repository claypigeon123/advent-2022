package com.cp.advent2022.data.day2.resolver;

import lombok.NonNull;

public interface MatchRoundResolver<ROUND> {
    int resolveRound(@NonNull ROUND matchRound);
}
