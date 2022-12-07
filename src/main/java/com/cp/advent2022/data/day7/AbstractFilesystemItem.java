package com.cp.advent2022.data.day7;

import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractFilesystemItem {

    protected String name;

    protected Directory parent;

    protected abstract void print(int levelsDeep);
}
