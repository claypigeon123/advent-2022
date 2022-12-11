package com.cp.advent2022.annotation;

import com.cp.advent2022.api.DayApi;
import com.cp.advent2022.component.AdventResourceLoader;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoadAdventResourceAdvice {

    private final AdventResourceLoader adventResourceLoader;

    @Before("@within(com.cp.advent2022.annotation.LoadAdventResource) || @annotation(LoadAdventResource)")
    public void loadAdventResource(JoinPoint jp) {
        DayApi api = (DayApi) jp.getThis();
        api.setLines(adventResourceLoader.loadAdventResource(api.getDay()));
    }
}
