
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SudokuTest {
  public static void main(String[] args) throws IOException {
    try(var file = new BufferedReader(new FileReader(args[0]))) {
      String sudokuStr;
      while((sudokuStr = file.readLine()) != null) {
        Sudoku s = new Sudoku();
        int i = 0;
        int j = 0;
        for(char c : sudokuStr.toCharArray()) {
          s.set(i, j, c);
          j += 1;
          if(j >= 9) {
            j = 0;
            i += 1;
          }
        }
        System.out.println("Read the following sudoku:");
        s.print();
        if(s.check()) {
          System.out.println("The sudoku is legal.");
        }
        System.out.println();
      }
    }
  }

}
