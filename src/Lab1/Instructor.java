package Lab1;

import java.util.List;

public class Instructor {
    private int instructorId;
    private String instructorName;
    private String department;
    private List<Course> course;

    public Instructor(int instructorId, String instructorName, String department, List<Course> course) {
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.department = department;
        this.course = course;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "instructorId=" + instructorId +
                ", instructorName='" + instructorName + '\'' +
                ", department='" + department + '\'' +
                ", course=" + course +
                '}';
    }
}
