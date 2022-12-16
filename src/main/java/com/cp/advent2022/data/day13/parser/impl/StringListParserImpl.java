package com.cp.advent2022.data.day13.parser.impl;

import com.cp.advent2022.data.day13.parser.StringListParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class StringListParserImpl implements StringListParser {

    private final ObjectMapper objectMapper;

    @Override
    public Object parse(String line) throws IOException {
        return objectMapper.readValue(line, Object.class);
    }
}
