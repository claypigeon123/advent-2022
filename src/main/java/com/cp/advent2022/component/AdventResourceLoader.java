package com.cp.advent2022.component;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class AdventResourceLoader {
    private final ResourceLoader resourceLoader;

    public List<String> loadAdventResource(int day) {
        Resource resource = resourceLoader.getResource(String.format("classpath:inputs/day%d.txt", day));

        if (!resource.exists()) {
            throw new RuntimeException(String.format("Resource for day %d was not found.", day));
        }

        List<String> lines = new ArrayList<>();

        try (
            Scanner scan = new Scanner(new BufferedInputStream(resource.getInputStream()))
        ) {
            while (scan.hasNextLine()) {
                lines.add(scan.nextLine());
            }
        } catch (IOException ioException) {
            throw new RuntimeException(String.format("IO Exception while reading resource for day %d", day), ioException);
        }

        return lines;
    }
}
