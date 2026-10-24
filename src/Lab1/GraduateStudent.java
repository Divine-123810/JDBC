package Lab1;

public class GraduateStudent extends Student{
    private double grades;
    private double researchBonus;


    public GraduateStudent(int StudentId,String StudentName,String EmailAddress,int age,String gender, double grades,double researchBonus) {
        super(StudentId,StudentName,EmailAddress,age,gender);
        this.grades = grades;
        this.researchBonus = researchBonus;
    }

    public double getGrades() {
        return grades;
    }

    public double getResearchBonus() {
        return researchBonus;
    }

    public void setGrades(double grades) {
        this.grades = grades;
    }

    public void setResearchBonus(double researchBonus) {
        this.researchBonus = researchBonus;
    }

    @Override
    public  double calculateGPA(){
        return ((grades * 50)/100)+researchBonus;
    }
}
