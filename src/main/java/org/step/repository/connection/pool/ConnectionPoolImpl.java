package org.step.repository.connection.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Savepoint;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPoolImpl implements ConnectionPool {

    private static volatile ConnectionPoolImpl connectionPool = new ConnectionPoolImpl();

    private Connection connection;
    private BlockingQueue<Connection> connectionBlockingQueue;

    private int databasePoolSize;

    private ConnectionPoolImpl() {
    }

    public static ConnectionPoolImpl getInstance() {
        ConnectionPoolImpl localInstance = connectionPool;

        if (localInstance == null) {
            synchronized (ConnectionPoolImpl.class) {
                localInstance = connectionPool;
                if (localInstance == null) {
                    connectionPool = localInstance = new ConnectionPoolImpl();
                }
            }
        }
        localInstance.init();
        return localInstance;
    }

    @Override
    public Connection getConnection() {
        Connection newConnection;

        if (connectionBlockingQueue.isEmpty() || connectionBlockingQueue.size() < databasePoolSize) {
            for (int i = 0; i < databasePoolSize - connectionBlockingQueue.size(); i++) {
                connectionBlockingQueue.add(connection);
            }
        }
        try {
            newConnection = connectionBlockingQueue.take();
        } catch (InterruptedException e) {
            newConnection = connectionBlockingQueue.poll();
        }
        return newConnection;
    }

    @Override
    public void releaseConnection(Connection connection) {
        try {
            connectionBlockingQueue.put(connection);
        } catch (InterruptedException e) {
            if (connectionBlockingQueue.size() >= databasePoolSize) {
                connectionBlockingQueue.remove();
            }
            connectionBlockingQueue.offer(connection);
        }
    }

    @Override
    public void commitTransaction(Connection connection) {

    }

    @Override
    public void rollbackTransaction(Connection connection) {

    }

    @Override
    public void rollbackTransactionWithSavePoint(Connection connection, Savepoint savepoint) {

    }

    @Override
    public Optional<Savepoint> setSavePoint(Connection connection, String savePointName) {
        return Optional.empty();
    }

    private void init() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();

        String url = dbResourceManager.getValue(DBParameter.DB_URL);
        String username = dbResourceManager.getValue(DBParameter.DB_USERNAME);
        String password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
        String driver = dbResourceManager.getValue(DBParameter.DB_DRIVER);

        try {
            Class.forName(driver);

            connection = DriverManager.getConnection(url, username, password);

            String poolSize = dbResourceManager.getValue(DBParameter.DB_POOL_SIZE);

            databasePoolSize = Integer.parseInt(poolSize);

            connectionBlockingQueue = new ArrayBlockingQueue<>(databasePoolSize);

            for (int i = 0; i < databasePoolSize; i++) {
                connectionBlockingQueue.add(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
