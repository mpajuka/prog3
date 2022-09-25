import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ComparisonTest {
  public static void main(String[] args) throws IOException {
    String attainmentFN = args[0];
    ArrayList<Attainment> attainments = new ArrayList<>();
    try(var file = new BufferedReader(new FileReader(attainmentFN))) {
      String line;
      while((line = file.readLine()) != null) {
        String[] studentCourseGrade = line.split(" ", 3);
        attainments.add(new Attainment(studentCourseGrade[1],
                studentCourseGrade[0], Integer.parseInt(studentCourseGrade[2])));
      }
    }
    System.out.format("Original order:%n%s%n", attainments);
    Collections.sort(attainments);
    System.out.format("Sorted in default order:%n%s%n", attainments);
    attainments.sort(Attainment.CODE_GRADE_CMP);
    System.out.format("Sorted using CODE_GRADE_CMP:%n%s%n", attainments);
    attainments.sort(Attainment.CODE_STUDENT_CMP);
    System.out.format("Sorted using CODE_STUDENT_CMP:%n%s%n", attainments);
  }

}
