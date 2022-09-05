
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentRegisterTest {
  public static void main(String[] args) throws IOException {
    String studentFN = args[0];
    String courseFN = args[1];
    String attFN = args[2];
    StudentRegister sr = new StudentRegister();
    try(var file = new BufferedReader(new FileReader(studentFN))) {
      String line;
      while((line = file.readLine()) != null) {
        String[] numberName = line.split(" ", 2);
        sr.addStudent(new Student(numberName[1], numberName[0]));
      }
    }
    System.out.println("All registered students:");
    for(Student st : sr.getStudents()) {
      System.out.println("  " + st.getName() + " " + st.getStudentNumber());
    }

    try(var file = new BufferedReader(new FileReader(courseFN))) {
      String line;
      while((line = file.readLine()) != null) {
        String[] codeCreditsName = line.split(" ", 3);
        sr.addCourse(new Course(codeCreditsName[0], codeCreditsName[2],
                Integer.parseInt(codeCreditsName[1])));
      }
    }
    System.out.println("All registered courses:");
    for(Course c : sr.getCourses()) {
      System.out.println("  " + c.getCode() + " " + c.getName() + " " + c
              .getCredits());
    }

    try(var file = new BufferedReader(new FileReader(attFN))) {
      String line;
      while((line = file.readLine()) != null) {
        String[] studentCourseGrade = line.split(" ", 3);
        sr.addAttainment(new Attainment(studentCourseGrade[1],
                studentCourseGrade[0], Integer.parseInt(studentCourseGrade[2])));
      }
    }

    System.out.format("Trying to print attainments of an unknown student:%n  ");
    sr.printStudentAttainments("007");

    System.out.println("Attainments for all students:");
    int i = 0;
    for(Student st : sr.getStudents()) {
      if(i == 0) {
        System.out.println("Listing in original order:");
        sr.printStudentAttainments(st.getStudentNumber());
      }
      else if(i == 1) {
        System.out.println("Listing in order of course name:");
        sr.printStudentAttainments(st.getStudentNumber(), "by name");
      }
      else {
        System.out.println("Listing in order of course code:");
        sr.printStudentAttainments(st.getStudentNumber(), "by code");
      }
      i = (i + 1) % 3;
    }
  }

}
