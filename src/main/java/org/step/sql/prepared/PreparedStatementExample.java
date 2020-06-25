package org.step.sql.prepared;

import org.step.model.Course;
import org.step.sql.statement.Runner;

import java.sql.*;
import java.util.stream.Stream;

public class PreparedStatementExample {

    final static String SQL = "INSERT INTO COURSE (id, description) VALUES (?, ?)";

    public static void main(String[] args) throws SQLException {
        Runner runner = new Runner();

        Connection connection = runner.getConnection();
//
//        CallableStatement callableStatement = connection.prepareCall("(call calculate_something(?, ?, ?))");
//
//        callableStatement.executeQuery();

//        createCourse(14, "new new description", connection);
        createManyCourses(
                connection,
                new Course(18, "18"),
                new Course(19, "19"),
                new Course(20, "20")
        );
        readCourseData(connection);
    }

    private static void createManyCourses(Connection connection, Course... courses) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        Stream.of(courses)
                .forEach(course -> {
                    try {
                        preparedStatement.setLong(1, course.getId());
                        preparedStatement.setString(2, course.getDescription());
                        preparedStatement.addBatch();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
        int[] ints = preparedStatement.executeBatch();

        Stream.of(ints)
                .forEach(System.out::println);
    }

    private static void createCourse(long id, String description, Connection connection) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setLong(1, id);
        preparedStatement.setString(2, description);

        int update = preparedStatement.executeUpdate();

        System.out.println(String.format("%d lines was inserted", update));
    }

    private static void readCourseData(Connection connection) throws SQLException {
        final String allData = "SELECT * FROM COURSE";

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(allData);

        while (resultSet.next()) {
            System.out.println(resultSet.getLong(1) + " " + resultSet.getString(2));
        }
    }
}
