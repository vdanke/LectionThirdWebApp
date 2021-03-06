package org.step.service;

import org.step.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    void delete(User user);

    List<User> findAllUsersAfterInsert(User user);

    List<User> findAllUsers();

    User findUserById(Long id);

    User login(String username, String password);
}
