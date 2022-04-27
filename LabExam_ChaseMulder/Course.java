import java.util.ArrayList;

public class Course {
    //Instance Variables
    private String name;
    private int section;
    private String semester;
    private int year;
    private int[] grades;

    //Constructors
    public Course() {
        this.name = "CIS162";
        this.section = 2;
        this.semester = "winter";
        this.year = 2020;

    }

    public Course(String n, int sect, String s, int y) {
        this.name = n;
        this.section = sect;
        this.semester = s;
        this.year = y;

    }

    //Accessor Methods
    public String getName() {
        return name;
    }   

    public int getSection() {
        return section;
    }

    public String getSemester() {
        return semester;
    }

    public int getYear() {
        return year;
    }

    public int[] getGrades() {
        return grades;
    }

    public int getNumberStudents () {
        return this.grades.length ;
    }

    //Setters
    public void setName(String n) {
        this.name = n;
    }

    public void setSection(int sect) {
        this.section = sect;
    }

    public void setSemester(String s) {
        this.semester = s;
    }

    public void setYear(int y) {
        this.year = y;
    }

    public void populateGrades(int[] nums) {
        this.grades = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            int score = nums[i];
            if(score > 100 || score < 0)
                this.grades[i] = 0;
            else
                this.grades[i] = score;
        }
    }

    //More Methods
    public int getAverage() {
        int n = this.grades.length;
        int sum = 0;
        for(int val : this.grades)
            sum += val;
        return sum/n;
    }

    public int getHighest() {
        int max = this.grades[0];
        for(int val : this.grades) {
            if(val > max)
                max = val;
        }
        return max;
    }

    public int countGrades(int value) {
        int count = 0;
        for(int grades : this.grades) {
            if(grades > value)
                count += 1;
        }
        return count;
    }

    public boolean changeGrade(int index, int grade) {
        if(index >= 0 && index < this.grades.length) {
            if(grade >= 0 && grade <= 100) {
                this.grades[index] = grade ;
                return true;
            }
            return false;
        }
        return false;
    }

    public void makeCurve(int value) {
        int temp;
        for(int i = 0; i < this.grades.length; i++) {
            temp = this.grades[i] + value;
            if(temp > 100)
                temp = 100;
            this.grades[i] = temp;
        }
    }

    private String convertGradeToLetter(int grade) {
        if(grade >= 93)
            return "A";
        if(grade >= 83)
            return "B";
        if(grade >= 73)
            return "C";
        if(grade >= 60)
            return "D";
        return "F";
    }

    public void printGrades() {
        for(int i = 0; i < this.grades.length; i++) {
            System.out.println((i+1) + ": "
                + this.grades[i] + " - " + convertGradeToLetter(this.grades[i]));
        }
    }

    public String toString() {
        return "" + this.name + " - "
        + this.section + " " + this.semester + " "
        + this.year + "\nAverage grade: " + this.getAverage() + " - "
        + convertGradeToLetter(this.getAverage()) + "\nHighest grade: "
        + this.getHighest() + " - "+ convertGradeToLetter(this.getHighest()) 
        + "\n\nGrades" + "\n======";
    }

    //Main
    public static void main(String args[]) {
        Course course = new Course();
        course.populateGrades((new int [] {93,70,84,75,76,81,90,94,65,72}));
        course.makeCurve(5);
        System.out.println(course);
        course.printGrades();
    }
}