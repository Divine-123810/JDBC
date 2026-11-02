package Lab2;

import Lab1.Student;

import java.sql.SQLException;
import java.util.List;

public interface EnrollmentDAO {

    int enroll(int StudentId, int courseId) throws SQLException;
    boolean updateMark(int StudentId , int CourseId, double mark) throws SQLException;
    boolean delete(int StudentId , int courseId) throws SQLException;
    void createTable() throws SQLException;
    void dropTable() throws  SQLException;

}
