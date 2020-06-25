package org.step.repository.connection.pool;

import java.sql.Connection;
import java.sql.Savepoint;
import java.util.Optional;

public interface ConnectionPool {

    Connection getConnection();

    void releaseConnection(Connection connection);

    void commitTransaction(Connection connection);

    void rollbackTransaction(Connection connection);

    void rollbackTransactionWithSavePoint(Connection connection, Savepoint savepoint);

    Optional<Savepoint> setSavePoint(Connection connection, String savePointName);
}
