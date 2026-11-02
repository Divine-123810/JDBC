package Lab2;

import Lab1.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {
    int create(Student student) throws SQLException;
    List<Student> getAllStudents() throws SQLException;
    Student findById(int StudentId) throws  SQLException;
    boolean updateStudentEmail(int StudentId,String newEmail) throws SQLException;
    boolean deleteById(int StudentId) throws SQLException;
}
