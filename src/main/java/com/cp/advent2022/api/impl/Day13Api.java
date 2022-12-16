package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.aspect.resloader.LoadAdventResource;
import com.cp.advent2022.data.day13.NodePair;
import com.cp.advent2022.data.day13.parser.StringListParser;
import com.cp.advent2022.data.day13.parser.impl.StringListParserImpl;
import com.cp.advent2022.data.day13.processor.CompareResult;
import com.cp.advent2022.data.day13.processor.PacketComparator;
import com.cp.advent2022.data.day13.processor.impl.PacketComparatorImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.ArrayList;
import java.util.List;

@Component
@LoadAdventResource(13)
@Command(name = "day13", mixinStandardHelpOptions = true)
public class Day13Api extends DayApi {

    private final StringListParser parser;
    private final PacketComparator comparator;

    public Day13Api(ObjectMapper objectMapper) {
        this.parser = new StringListParserImpl(objectMapper);
        this.comparator = new PacketComparatorImpl();
    }

    @Override
    protected void execute() throws Exception {
        List<NodePair> pairs = new ArrayList<>();

        NodePair rollingNodePair = new NodePair();
        for (String line : lines) {
            if (line.isBlank()) continue;

            Object node = parser.parse(line);

            if (rollingNodePair.getLeft() == null) {
                rollingNodePair.setLeft(node);
                continue;
            }

            if (rollingNodePair.getRight() == null) {
                rollingNodePair.setRight(node);
                pairs.add(rollingNodePair);
                rollingNodePair = new NodePair();
            }
        }

        int sum = 0;
        for (int i = 0; i < pairs.size(); i++) {
            NodePair pair = pairs.get(i);
            CompareResult result = comparator.compare(pair.getLeft(), pair.getRight());

            if (result == CompareResult.RIGHT_ORDER) {
                sum += i + 1;
            }
        }

        // ---

        System.out.printf("1. The sum of indices of pairs in the right order is %d.\n\n", sum);
    }
}
