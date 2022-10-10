import java.util.Comparator;

public class Attainment implements Comparable<Attainment> {

    private String courseCode;
    private String studentNumber;
    private int grade;

    public static final Comparator<Attainment> CODE_GRADE_CMP =
            new Comparator<Attainment>() {
        @Override
        public int compare(Attainment o1, Attainment o2) {
            int i = o1.courseCode.compareTo(o2.courseCode);
            if (i != 0) return i;

            i = Integer.compare(o2.grade, o1.grade);
            if (i != 0) return i;

            return 0;
        }
    };
    public static final Comparator<Attainment> CODE_STUDENT_CMP =
            new Comparator<Attainment>() {
        @Override
        public int compare(Attainment o1, Attainment o2) {
            int i = o1.courseCode.compareTo(o2.courseCode);
            if (i != 0) return i;

            i = o1.studentNumber.compareTo(o2.studentNumber);
            if (i != 0) return i;

            return 0;
        }
    };
    @Override
    public int compareTo(Attainment o) {
        int i = this.studentNumber.compareTo(o.studentNumber);
        if (i != 0) return i;

        i = this.courseCode.compareTo(o.courseCode);
        if (i != 0) return i;

        return 0;
    }

    public Attainment(String courseCode, String studentNumber, int grade) {
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
        return String.format("%s %s %d%n", courseCode, studentNumber, grade);
    }
}
