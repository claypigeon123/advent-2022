package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.aspect.resloader.LoadAdventResource;
import com.cp.advent2022.data.day13.NodePair;
import com.cp.advent2022.data.day13.comparator.PacketComparator;
import com.cp.advent2022.data.day13.parser.StringListParser;
import com.cp.advent2022.data.day13.parser.impl.StringListParserImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@LoadAdventResource(13)
@Command(name = "day13", mixinStandardHelpOptions = true)
public class Day13Api extends DayApi {

    private final StringListParser parser;
    private final PacketComparator comparator;

    public Day13Api(ObjectMapper objectMapper) {
        this.parser = new StringListParserImpl(objectMapper);
        this.comparator = new PacketComparator();
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

        // Part 1
        int sum = 0;
        for (int i = 0; i < pairs.size(); i++) {
            NodePair pair = pairs.get(i);
            if (comparator.compare(pair.getLeft(), pair.getRight()) > 0) {
                sum += i + 1;
            }
        }

        // Part 2
        List<Object> sorted = new ArrayList<>(pairs).stream()
            .flatMap(pair -> Stream.of(pair.getLeft(), pair.getRight()))
            .collect(Collectors.toList());

        Object dividerPacketOne = parser.parse("[[2]]");
        Object dividerPacketTwo = parser.parse("[[6]]");

        sorted.addAll(List.of(dividerPacketOne, dividerPacketTwo));

        sorted = sorted.stream()
            .sorted(comparator.reversed())
            .toList();

        int decoderKey = (sorted.indexOf(dividerPacketOne) + 1) * (sorted.indexOf(dividerPacketTwo) + 1);

        // ---

        System.out.printf("1. The sum of indices of pairs in the right order is %d.\n\n", sum);

        System.out.printf("2. The decoder key for this distress signal is %d.\n\n", decoderKey);
    }
}
