package org.step.repository.impl;

import org.step.model.User;
import org.step.repository.UserRepository;
import org.step.repository.connection.pool.ConnectionPool;
import org.step.repository.connection.pool.ConnectionPoolImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private static final String INSERT_NEW_USER = "INSERT INTO USERS_SECOND (username, password, fullName) VALUES (?, ?, ?)";
    private static final String DELETE_EXIST_USER = "DELETE FROM USERS_SECOND WHERE id = ?";
    private static final String FIND_ALL_USERS_SECOND = "SELECT * FROM USERS_SECOND";
    private static final String LOGIN_USER = "SELECT * FROM USERS_SECOND WHERE USERNAME=? AND PASSWORD=?";

    private ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    @Override
    public User save(User user) {
        Connection connection = connectionPool.getConnection();

        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_USER);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());

            int i = preparedStatement.executeUpdate();

            if (i != -1) {
                connectionPool.commitTransaction(connection);
                return user;
            } else {
                connectionPool.rollbackTransaction(connection);
                return null;
            }
        } catch (SQLException e) {
            connectionPool.rollbackTransaction(connection);
            e.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return null;
    }

    @Override
    public void delete(User user) {
        Connection connection = connectionPool.getConnection();

        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EXIST_USER);

            preparedStatement.setLong(1, user.getId());

            int i = preparedStatement.executeUpdate();

            if (i != -1) {
                connectionPool.commitTransaction(connection);
            } else {
                connectionPool.rollbackTransaction(connection);
            }
        } catch (SQLException e) {
            connectionPool.rollbackTransaction(connection);
            e.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<User> findAllUsersAfterInsert(User user) {
        Connection connection = connectionPool.getConnection();
        Savepoint savepoint = null;
        List<User> userList = new ArrayList<>();

        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_USER);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());

            int i = preparedStatement.executeUpdate();

            if (i != -1) {
                Optional<Savepoint> first = connectionPool.setSavePoint(connection, "first");

                savepoint = first
                        .orElseThrow(SQLException::new);

                System.out.println("First transaction is committed");
            } else {
                connectionPool.rollbackTransaction(connection);
            }
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS_SECOND);

            if (resultSet == null) {
                connectionPool.rollbackTransactionWithSavePoint(connection, savepoint);
            } else {
                while (resultSet.next()) {
                    userList.add(
                            new User(
                                    resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4)
                            )
                    );
                }
                connectionPool.commitTransaction(connection);
                System.out.println("Second transaction is committed");
            }
            return userList;
        } catch (SQLException e) {
            connectionPool.rollbackTransaction(connection);
            e.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return userList;
    }

    @Override
    public User login(String username, String password) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_USER);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new User(resultSet.getInt(1), resultSet.getString(4), resultSet.getString(2), resultSet.getString(3));
            }
        } catch (SQLException e) {
            return null;
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.empty();
    }
}
