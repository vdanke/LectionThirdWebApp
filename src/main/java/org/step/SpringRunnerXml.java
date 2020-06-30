package org.step;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.step.spring.example.Coach;

public class SpringRunnerXml {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

        Coach firstCoach = context.getBean("myCoach", Coach.class);
        Coach secondCoach = context.getBean("myCoach", Coach.class);

        System.out.println(firstCoach == secondCoach);

        String training = firstCoach.getTraining();

        System.out.println(training);

        context.close();
    }
}
