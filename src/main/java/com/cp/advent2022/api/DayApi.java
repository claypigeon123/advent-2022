package com.cp.advent2022.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
public abstract class DayApi extends Api {
    @Getter
    protected final int day;

    @Setter
    protected List<String> lines;
}
