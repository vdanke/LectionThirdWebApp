package org.step.spring.example;

public class TennisCoach implements Coach {

    private final GoodLuck goodLuck;

    private String saySomething;

    public TennisCoach(GoodLuck goodLuck) {
        this.goodLuck = goodLuck;
    }

    @Override
    public String getTraining() {
        return "Get hard Tennis training " + goodLuck.sayGoodWords() + " " + saySomething;
    }

    public String getSaySomething() {
        return saySomething;
    }

    public void setSaySomething(String saySomething) {
        this.saySomething = saySomething;
    }
}
