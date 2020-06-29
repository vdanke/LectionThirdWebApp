package org.step.service.impl;

import org.step.model.User;
import org.step.repository.UserRepository;
import org.step.repository.impl.UserRepositoryImpl;
import org.step.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public User save(User user) {
        if (user == null) {
            throw new IllegalStateException("User is null");
        }
        if (user.getUsername() == null || user.getPassword() == null || user.getFullName() == null) {
            throw new IllegalStateException("Something is null");
        }
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> findAllUsersAfterInsert(User user) {
        return userRepository.findAllUsersAfterInsert(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new IllegalStateException("User not found"));
    }

    @Override
    public User login(String username, String password) {
        return userRepository.login(username, password);
    }
}
