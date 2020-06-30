package org.step.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.step.spring.example.Coach;
import org.step.spring.example.GoodLuck;
import org.step.spring.example.HaveGoodDay;
import org.step.spring.example.TennisCoach;

@Configuration
public class ExampleConfiguration {

    @Bean(name = "goodDay")
    public GoodLuck getGoodDayLuck() {
        return new HaveGoodDay();
    }

    @Bean(name = "tennisCoach")
    @Scope("prototype")
    public Coach getTennisCoach() {
        TennisCoach tennisCoach = new TennisCoach(getGoodDayLuck());
        tennisCoach.setSaySomething("Say something");
        return tennisCoach;
    }
}
