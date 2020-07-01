package org.step.repository;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Example {

    public Example() {
        System.out.println("Create bean");
    }

    @PostConstruct
    public void init() {
        System.out.println("Init bean");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroy bean");
    }
}
