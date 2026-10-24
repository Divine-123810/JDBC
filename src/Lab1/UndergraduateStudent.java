package Lab1;

public class UndergraduateStudent extends Student{
    private double grades;

    public UndergraduateStudent(int studentId, String studentName, String emailAddress, int age, String gender, double grades) {
        super(studentId, studentName, emailAddress, age, gender);
        this.grades = grades;
    }

    public double getGrades() {
        return grades;
    }

    public void setGrades(double grades) {
        this.grades = grades;
    }

    @Override
    public double calculateGPA(){
        return (grades * 20)/100;
    }
}
