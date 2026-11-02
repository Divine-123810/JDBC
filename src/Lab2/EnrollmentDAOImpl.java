package Lab2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EnrollmentDAOImpl implements EnrollmentDAO {

    @Override
    public int enroll(int studentId, int courseId) throws SQLException {
        String sql = """
                INSERT INTO enrollments (student_id, course_id)
                VALUES (?, ?)
                RETURNING enrollment_id
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);
            statement.setInt(2, courseId);

            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                return rs.getInt("enrollment_id");
            }
        }
    }

    @Override
    public boolean updateMark(int studentId, int courseId, double mark) throws SQLException {
        if (!Double.isFinite(mark) || mark < 0 || mark > 100) {
            throw new IllegalArgumentException("Mark must be between 0 and 100.");
        }
        String sql = """
                UPDATE enrollments
                SET mark = ?
                WHERE student_id = ? AND course_id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, mark);
            statement.setInt(2, studentId);
            statement.setInt(3, courseId);
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int studentId, int courseId) throws SQLException {
        String sql = "DELETE FROM enrollments WHERE student_id = ? AND course_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public void createTable() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS enrollments (
                    enrollment_id   SERIAL PRIMARY KEY,
                    student_id      INT  NOT NULL REFERENCES students(student_id) ON DELETE CASCADE,
                    course_id       INT  NOT NULL REFERENCES courses(course_id)   ON DELETE CASCADE,
                    mark            NUMERIC(5,2) CHECK (mark BETWEEN 0 AND 100),
                    enrollment_date DATE NOT NULL DEFAULT CURRENT_DATE,
                    UNIQUE (student_id, course_id)
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
            statement.execute("DROP TABLE IF EXISTS enrollment, enrollments CASCADE");

        }
    }
}