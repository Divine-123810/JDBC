package Lab2;

import Lab1.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    @Override
    public int create(Course course) throws SQLException {
        String sql = """
                INSERT INTO courses (course_name, credit_hours, department)
                VALUES (?, ?, ?)
                RETURNING course_id
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, course.getCourseName());
            statement.setInt(2, course.getCreditHours());
            statement.setString(3, course.getDepartment());

            try (ResultSet result = statement.executeQuery()) {
                result.next();
                return result.getInt("course_id");
            }
        }
    }

    @Override
    public List<Course> findAll() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = """
                SELECT course_id, course_name, credit_hours, department
                FROM courses
                ORDER BY course_id
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                courses.add(mapRow(result));
            }
        }
        return courses;
    }

    @Override
    public Course findById(int courseId) throws SQLException {
        String sql = """
                SELECT course_id, course_name, credit_hours, department
                FROM courses WHERE course_id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, courseId);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? mapRow(result) : null;
            }
        }
    }

    @Override
    public boolean deleteById(int courseId) throws SQLException {
        String sql = "DELETE FROM courses WHERE course_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, courseId);
            return statement.executeUpdate() > 0;
        }
    }

    private Course mapRow(ResultSet result) throws SQLException {
        return new Course(
                result.getInt("course_id"),
                result.getString("course_name"),
                result.getInt("credit_hours"),
                result.getString("department"));
    }


    @Override
    public void createTable() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS courses (
                    course_id    SERIAL PRIMARY KEY,
                    course_name  VARCHAR(50) NOT NULL,
                    credit_hours INT         NOT NULL CHECK (credit_hours > 0),
                    department   VARCHAR(100)
                )
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    @Override
    public void dropTable() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS courses CASCADE");

        }
    }
}