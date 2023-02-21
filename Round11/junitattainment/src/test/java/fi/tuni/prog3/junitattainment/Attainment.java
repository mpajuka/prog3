package fi.tuni.prog3.junitattainment;

/**
 *
 * @author miskapajukangas
 */
public class Attainment implements Comparable<Attainment> {
    private String courseCode;
    private String studentNumber;
    private int grade;
    
    @Override
    public int compareTo(Attainment o) {
        int i = this.studentNumber.compareTo(o.studentNumber);
        if (i != 0) return i;
        
        i = this.courseCode.compareTo(o.courseCode);
        if (i != 0) return i;
        
        return 0;
    }
    
    public Attainment(String courseCode, String studentNumber, int grade) 
            throws IllegalArgumentException {
        if (grade > 5 || grade < 0) {
            throw new IllegalArgumentException("The grade must belong to the "
                    + "interval between 0 and 5");
        }
        if (courseCode == null || studentNumber == null) {
            throw new IllegalArgumentException("The provided course code or "
                    + "Studentnumber is invalid");
        }
        
        this.courseCode = courseCode;
        this.studentNumber = studentNumber;
        this.grade = grade;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d", courseCode, studentNumber, grade);
    }
}