package com.cp.advent2022.data.day13.parser;

import java.io.IOException;

public interface StringListParser {
    Object parse(String line) throws IOException;
}
