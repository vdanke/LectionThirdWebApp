package org.step.repository.impl;

import org.step.model.User;
import org.step.repository.UserRepository;
import org.step.repository.connection.pool.ConnectionPool;
import org.step.repository.connection.pool.ConnectionPoolImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {

    private static final String INSERT_NEW_USER = "INSERT INTO USERS (id, username, password, full_name) VALUES (?, ?, ?, ?)";

    private ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    @Override
    public User save(User user) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_USER);

            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getFullName());

            int i = preparedStatement.executeUpdate();

            if (i != -1) {
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return null;
    }
}
