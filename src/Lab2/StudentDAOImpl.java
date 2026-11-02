package Lab2;


import Lab1.GraduateStudent;
import Lab1.Student;
import Lab1.UndergraduateStudent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentDAOImpl implements StudentDAO {

    private static final String COLUMNS="student_id, student_name, email, gender,age,department,year_of_study,student_type";

    @Override
    public int create(Student student) throws SQLException {
        String sql = """
                INSERT INTO students
                (student_name,email,gender,age,department,year_of_study,student_type)
                values(?,?,?,?,?,?,?) 
                returning student_id
                
                """;
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)){

            pst.setString(1,student.getStudentName());
            pst.setString(2, student.getEmailAddress());
            pst.setString(3, student.getGender());
            pst.setInt(4,student.getAge());
            pst.setString(5, student.getDepartment());
            pst.setInt(6,student.getYearOfStudy());
            pst.setString(7, student.getStudentType());

            try(ResultSet rs = pst.executeQuery()){
                rs.next();
                return  rs.getInt("student_id");
            }
        }
    }

    @Override
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT " + COLUMNS + " FROM students ORDER BY student_id";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery()){
            while(rs.next()){
                students.add(mapRow(rs));
            }
        }
        return students;
    }

    @Override
    public Student findById(int StudentId) throws SQLException {
        String sql ="SELECT " + COLUMNS + " FROM students WHERE student_id = ?";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)){

            pst.setInt(1,StudentId);
            try(ResultSet rs = pst.executeQuery()){
                return rs.next() ? mapRow(rs) :null;
            }
        }
    }

    @Override
    public boolean updateStudentEmail(int StudentId, String newEmail) throws SQLException {

        String sql = "UPDATE students SET email = ? WHERE student_id = ?";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setString(1,newEmail);
            pst.setInt(2,StudentId);
            return  pst.executeUpdate() > 0 ;
        }
    }

    @Override
    public boolean deleteById(int StudentId) throws SQLException {
        String sql = "DELETE FROM students WHERE student_id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setInt(1,StudentId);
            return  pst.executeUpdate() >0;
        }
    }

    private Student mapRow(ResultSet rs) throws  SQLException{

        int id = rs.getInt("student_id");
        String name = rs.getString("student_name");
        String email = rs.getString("email");
        String gender = rs.getString("gender");
        int age = rs.getInt("age");
        String department = rs.getString("department");
        int year = rs.getInt("year_of_study");

        if ("Graduate".equals(rs.getString("student_type"))) {
            return new GraduateStudent(id, name, email, age, gender, department, year);
        }
        return new UndergraduateStudent(id, name, email, age, gender, department, year);
    }

    @Override
    public void createTable() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS students (
                    student_id    SERIAL PRIMARY KEY,
                    student_name  VARCHAR(50)  NOT NULL,
                    email         VARCHAR(100) UNIQUE,
                    gender        VARCHAR(10)  CHECK (gender IN ('female', 'male')),
                    age           INT          CHECK (age >= 16),
                    department    VARCHAR(100),
                    year_of_study INT,
                    student_type  VARCHAR(20)  NOT NULL DEFAULT 'Undergraduate'
                                  CHECK (student_type IN ('Undergraduate', 'Graduate'))
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
            statement.execute("DROP TABLE IF EXISTS students CASCADE");
        }
    }
}