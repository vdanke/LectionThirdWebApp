package org.step.sql.statement;

import java.sql.*;

public class Runner {

    public static void main(String[] args) throws Exception {
        Runner runner = new Runner();

        Connection connection = runner.getConnection();

        Statement createStatement = connection.createStatement();

        int i = createStatement
                .executeUpdate("INSERT INTO course (id, description) VALUES (12, 'NewUser')");

        System.out.println(i);

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM COURSE");

        while (resultSet.next()) {
            System.out.println(resultSet.getLong(1) +
                    " " + resultSet.getString("description"));
        }
        runner.closeConnection(connection, statement, resultSet);

//        Connection connection = null;

//        try {
//            Class.forName("org.postgresql.Driver");
//
//            connection = DriverManager
//                    .getConnection("jdbc:postgresql://localhost:5432/social", "user", "userpassword");
//
//            System.out.println(connection.toString());
//
//            DatabaseMetaData metaData = connection.getMetaData();
//
//            String userName = metaData.getUserName();
//            String databaseProductName = metaData.getDatabaseProductName();
//
//            System.out.println(userName);
//            System.out.println(databaseProductName);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            connection.close();
//        }
    }

    public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/social", "user", "userpassword");

            return connection;
        } catch (Exception e) {
            System.out.println(e.toString());
            return connection;
        }
    }

    public void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        try {;
            statement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
