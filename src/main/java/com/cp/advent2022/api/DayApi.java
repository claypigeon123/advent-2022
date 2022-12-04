package com.cp.advent2022.api;

import com.cp.advent2022.component.AdventResourceLoader;

import java.util.List;

public abstract class DayApi extends Api {
    private final AdventResourceLoader adventResourceLoader;

    protected final int day;
    protected List<String> lines;

    public DayApi(int day, AdventResourceLoader adventResourceLoader) {
        this.day = day;
        this.adventResourceLoader = adventResourceLoader;
    }

    @Override
    protected void initialize() {
        this.lines = loadDayResource();
    }

    protected List<String> loadDayResource() {
        return adventResourceLoader.loadAdventResource(day);
    }
}
