package com.cp.advent2022.api;

import lombok.Setter;

import java.util.List;

public abstract class DayApi extends Api {
    @Setter
    protected List<String> lines;
}
