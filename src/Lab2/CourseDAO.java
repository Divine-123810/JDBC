package Lab2;

import Lab1.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDAO {
    int create(Course course) throws SQLException;
    List<Course> findAll() throws SQLException;
    Course findById (int courseId) throws  SQLException;
    boolean deleteById(int courseId) throws SQLException;
    void createTable() throws SQLException;
    void dropTable() throws SQLException;
}
