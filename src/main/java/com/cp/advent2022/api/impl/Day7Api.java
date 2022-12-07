package com.cp.advent2022.api.impl;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.component.AdventResourceLoader;
import com.cp.advent2022.data.day7.AbstractFilesystemItem;
import com.cp.advent2022.data.day7.Directory;
import com.cp.advent2022.data.day7.File;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.LinkedList;
import java.util.Queue;

@Component
@Command(name = "day7", mixinStandardHelpOptions = true)
public class Day7Api extends DayApi {

    private static final String COMMAND_PREFIX = "$";
    private static final String COMMAND_CD = String.format("%s cd ", COMMAND_PREFIX);
    private static final String BACK = "..";

    public Day7Api(AdventResourceLoader adventResourceLoader) {
        super(7, adventResourceLoader);
    }

    @Override
    protected void execute() {
        Directory root = establishFileSystem();

        long sizeSumPartOne = sumDirectoriesUnderSize(root, 100_000);

        // --

        System.out.printf("1. Sum of the size of directories under 100000 is %d.\n\n", sizeSumPartOne);
    }

    private Directory establishFileSystem() {
        Directory root = new Directory("/", null);

        Directory pwd = root;
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);

            if (line.startsWith(COMMAND_PREFIX) ) {
                if (line.startsWith(COMMAND_CD)) {
                    String moveTo = line.substring(line.lastIndexOf(COMMAND_CD) + COMMAND_CD.length());

                    if (moveTo.equals(BACK) && pwd.getParent() != null) {
                        pwd = pwd.getParent();
                    }

                    if (pwd.getChildren().containsKey(moveTo) && pwd.getChildren().get(moveTo) instanceof Directory e) {
                        pwd = e;
                    }
                }
                continue;
            }

            String[] split = line.split(" ");
            String firstToken = split[0];
            String name = split[1];

            if (firstToken.equals("dir")) {
                pwd.getChildren().put(name, new Directory(name, pwd));
                continue;
            }

            pwd.getChildren().put(name, new File(name, pwd, Long.parseLong(firstToken)));
        }

        return root;
    }

    private long sumDirectoriesUnderSize(Directory root, long max) {
        long sizeSum = 0;

        Queue<Directory> directoryQueue = new LinkedList<>();
        directoryQueue.offer(root);

        while (directoryQueue.peek() != null) {
            Directory current = directoryQueue.poll();
            long size = current.size();

            if (size <= max) {
                sizeSum += size;
            }

            for (AbstractFilesystemItem item : current.getChildren().values()) {
                if (item instanceof Directory d) {
                    directoryQueue.offer(d);
                }
            }
        }

        return sizeSum;
    }
}
