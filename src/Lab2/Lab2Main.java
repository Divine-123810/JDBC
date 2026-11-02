package Lab2;

import Lab1.Course;
import Lab1.Student;
import Lab1.UndergraduateStudent;

import java.sql.SQLException;

public class Lab2Main {
    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAOImpl();
        CourseDAO courseDAO = new CourseDAOImpl();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl();

        try {

            studentDAO.createTable();
            courseDAO.createTable();
            enrollmentDAO.createTable();

            int studentId = studentDAO.create(new UndergraduateStudent(
                    0, "Demo Student", "demo.student@example.com", 20, "female", "Software Development", 1));
            int courseId = courseDAO.create(new Course(0, "Demo Course", 3, "Information Technology"));
            enrollmentDAO.enroll(studentId, courseId);

            System.out.println("--- Students ---");
            for (Student student : studentDAO.getAllStudents()) {
                System.out.println(student);
            }
            System.out.println("\n--- Courses ---");
            for (Course course : courseDAO.findAll()) {
                System.out.println(course);
            }

            enrollmentDAO.updateMark(studentId, courseId, 88.5);
            studentDAO.updateStudentEmail(studentId, "demo.updated@example.com");

            System.out.println("\nStudent deleted: " + studentDAO.deleteById(studentId));
            System.out.println("Course deleted:  " + courseDAO.deleteById(courseId));

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}