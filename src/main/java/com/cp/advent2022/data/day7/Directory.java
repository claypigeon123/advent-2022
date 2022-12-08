package com.cp.advent2022.data.day7;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("StringBufferReplaceableByString")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Directory extends AbstractFilesystemItem {

    private Map<String, AbstractFilesystemItem> children;

    public Directory(String name, Directory parent) {
        super(name, parent);
        this.children = new HashMap<>();
    }

    public long size() {
        long size = 0;

        for (AbstractFilesystemItem child : children.values()) {
            if (child instanceof File file) {
                size += file.getSize();
            } else if (child instanceof Directory dir) {
                size += dir.size();
            }
        }

        return size;
    }

    @Override
    public void print(int levelsDeep) {
        StringBuilder base = new StringBuilder()
            .append(" ".repeat(Math.max(0, levelsDeep)))
            .append("- ")
            .append(name)
            .append(" (dir)");

        System.out.println(base);

        for (AbstractFilesystemItem item : children.values()) {
            item.print(levelsDeep + 2);
        }
    }
}
