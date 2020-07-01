package org.step;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.step.model.User;
import org.step.repository.UserRepository;
import org.step.service.UserService;
import org.step.service.impl.UserServiceImpl;
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
        UserService userService = context.getBean("userService", UserService.class);

        System.out.println(userService);

        List<User> allUsers = userRepository.findAllUsers();

        allUsers.forEach(u -> System.out.println(u.toString()));

        context.close();
    }
}
