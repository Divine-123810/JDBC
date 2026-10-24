package Lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Student {
    private int StudentId;
    private String StudentName;
    private String EmailAddress;
    private int age;
    private String gender;
    private String department;
    private Set<Course> course;

    public Student(int studentId, String studentName, String emailAddress, int age, String gender) {
       this.StudentId = studentId;
       this.StudentName = studentName;
       this.EmailAddress = emailAddress;
        this.age = age;
        this.gender = gender;

    }

    public Student(int studentId, String studentName, String emailAddress, int age, String gender, String department,Set<Course> course) {
        this.StudentId = studentId;
        this.StudentName = studentName;
        this.EmailAddress = emailAddress;
        this.age = age;
        this.gender = gender;
        this.department= department;
        this.course = course;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Set<Course> getCourse() {
        return course;
    }

    public void setCourse(Set<Course> course) {
        this.course = course;
    }

    public double calculateGPA(){
        return 0.0;
    }

    @Override
    public String toString() {
        return "Student{" +
                "StudentId=" + StudentId +
                ", StudentName='" + StudentName + '\'' +
                ", EmailAddress='" + EmailAddress + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", department='" + department + '\'' +
                ", course=" + course +
                '}';
    }
}
