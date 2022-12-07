package com.cp.advent2022.data.day7;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@SuppressWarnings("StringBufferReplaceableByString")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class File extends AbstractFilesystemItem {

    private long size;

    public File(String name, Directory parent, long size) {
        super(name, parent);
        this.size = size;
    }

    @Override
    protected void print(int levelsDeep) {
        StringBuilder base = new StringBuilder()
            .append(" ".repeat(Math.max(0, levelsDeep)))
            .append("- ")
            .append(name)
            .append(" (file, size=")
            .append(size)
            .append(")");

        System.out.println(base);
    }
}
