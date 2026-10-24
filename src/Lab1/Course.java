package Lab1;

public class Course {

    private String courseId;
    private String courseName;
    private int creditHours;
    private Instructor instructor;

    public Course(String courseId, String courseName, int creditHours, Instructor instructor) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.instructor = instructor;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", creditHours=" + creditHours +
                ", instructor=" + instructor +
                '}';
    }
}
