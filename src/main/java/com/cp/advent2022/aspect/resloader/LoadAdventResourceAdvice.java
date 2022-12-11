package com.cp.advent2022.aspect.resloader;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.component.AdventResourceLoader;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoadAdventResourceAdvice {

    private final AdventResourceLoader adventResourceLoader;

    @Before(value = "@within(annotation) && target(api)", argNames = "annotation,api")
    public void loadAdventResource(LoadAdventResource annotation, DayApi api) {
        api.setLines(adventResourceLoader.loadAdventResource(annotation.value()));
    }
}
