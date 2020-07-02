package org.step.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.step.model.User;
import org.step.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private final User user = new User("first", "first", "first");

    @PostConstruct
    public void init() throws SQLException {
        System.out.println("Init method");
    }

    @PreDestroy
    public void destroy() throws SQLException {
        System.out.println("Destroy method");
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
        return new ArrayList<>();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public User login(String username, String password) {
        return null;
    }
}
