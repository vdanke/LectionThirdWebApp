package org.step.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.step.model.User;
import org.step.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final DataSource dataSource;
    private final User user = new User(500, "test", "test", "test");

    @Autowired
    public UserRepositoryImpl(@Qualifier("dataSource") DataSource data) {
        this.dataSource = data;
    }

    @PostConstruct
    public void init() throws SQLException {
        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO USERS_SECOND(fullname, username, password, id) values (?, ?, ?, ?)");

        preparedStatement.setString(1, user.getFullName());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setInt(4, user.getId());

        preparedStatement.executeUpdate();
    }

    @PreDestroy
    public void destroy() throws SQLException {
        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = connection
                .prepareStatement("DELETE FROM USERS_SECOND WHERE ID=?");

        preparedStatement.setLong(1, user.getId());

        preparedStatement.executeUpdate();
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
        List<User> userList = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS_SECOND");

            while (resultSet.next()) {
                userList.add(new User(resultSet.getInt(1), resultSet.getString(4), resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
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
