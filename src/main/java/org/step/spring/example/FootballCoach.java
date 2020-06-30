package org.step.spring.example;

public class FootballCoach implements Coach {

    private final GoodLuck goodLuck;

    public FootballCoach(GoodLuck goodLuck) {
        this.goodLuck = goodLuck;
    }

    @Override
    public String getTraining() {
        return "Get hard Football training " + goodLuck.sayGoodWords();
    }
}
