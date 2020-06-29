package org.step.sql;

import org.step.model.User;
import org.step.repository.UserRepository;
import org.step.repository.impl.UserRepositoryImpl;

import java.util.List;

public class RunnerWithSavepointExample {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl();

        User user = new User(128, "123", "123", "123");

        List<User> allUsersAfterInsert = userRepository.findAllUsersAfterInsert(user);

        allUsersAfterInsert
                .forEach(u -> System.out.println(u.toString()));
    }
}
