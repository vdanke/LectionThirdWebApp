package org.step.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.step.model.User;
import org.step.repository.UserRepository;
import org.step.service.UserService;

import java.util.List;

/*
@Service
@Repository
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public List<User> findAllUsersAfterInsert(User user) {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public User findUserById(Long id) {
        return null;
    }

    @Override
    public User login(String username, String password) {
        return null;
    }
}
