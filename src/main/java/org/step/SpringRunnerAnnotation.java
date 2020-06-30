package org.step;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.step.configuration.ExampleConfiguration;
import org.step.model.User;
import org.step.repository.UserRepository;
import org.step.spring.example.Coach;

import java.util.List;

public class SpringRunnerAnnotation {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.step");

        Coach tennisCoach = context.getBean("tennisCoach", Coach.class);
        Coach second = context.getBean("tennisCoach", Coach.class);

        System.out.println(tennisCoach == second);

        System.out.println(tennisCoach.getTraining());

        UserRepository userRepository = context.getBean("userRepository", UserRepository.class);

        List<User> allUsers = userRepository.findAllUsers();

        allUsers.forEach(u -> System.out.println(u.toString()));

        context.close();
    }
}
