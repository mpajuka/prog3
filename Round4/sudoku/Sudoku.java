import java.util.ArrayList;
import java.util.HashSet;

public class Sudoku {

    private ArrayList<ArrayList<Character>> sudokuMap = new ArrayList<>();

    public void set(int i, int j, char c) {
        int cInt = Character.getNumericValue(c);
        sudokuMap.add(new ArrayList<>());

        if (i > 8 || j > 8) {
            System.out.format("Trying to access illegal cell (%d, %d)!%n",
                                i, j);
        }

        else if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' ||
            c == '6' || c == '7' || c == '8' || c == '9' || c == ' ') {
            sudokuMap.get(i).add(j, c);
        } else {
            System.out.format("Trying to set illegal character %c to (%d, %d)!%n"
                                , c, i, j);
            sudokuMap.get(i).add(j, ' ');

        }

    }
    
    public void print() {
        System.out.println("#####################################");

        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (j == 0 || j == 3 || j == 6 ) {
                    System.out.format("# %c ", sudokuMap.get(i).get(j));
                }
                else if (j == 1 || j == 2 || j == 4 || j == 5 || j == 7) {
                    System.out.format("| %c ", sudokuMap.get(i).get(j));
                } 
                else {
                    System.out.format("| %c #", sudokuMap.get(i).get(j));
                }
                       

            }
            System.out.println();
            if (i == 2 || i == 5 || i == 8) {
                System.out.println("#####################################");
            
            } else {
                System.out.println("#---+---+---#---+---+---#---+---+---#");
            }
        }
    }
        
     

    public boolean check() {
        
        for (int row = 0; row < 9; row++) {
            HashSet<Character> set = new HashSet<>();
            for (int col = 0; col < 9; col++) {

                if (set.contains(sudokuMap.get(row).get(col))) {
                    System.out.format("Row %d has multiple %c's!%n", row,
                                        sudokuMap.get(row).get(col));
                    return false;

                } else if (sudokuMap.get(row).get(col) != ' ') {
                    set.add(sudokuMap.get(row).get(col));
                }

                } 
            }

        for (int col = 0; col < 9; col++) {
            HashSet<Character> set = new HashSet<>();
            for (int row = 0; row < 9; row++) {

                if (set.contains(sudokuMap.get(row).get(col))) {
                    System.out.format("Column %d has multiple %c's!%n", col,
                                        sudokuMap.get(row).get(col));
                    return false;

                } else if (sudokuMap.get(row).get(col) != ' ') {
                    set.add(sudokuMap.get(row).get(col));
                }

                } 
            }  
            
        for (int row = 0; row < 9; row = row + 3) {
            for (int col = 0; col < 9; col = col + 3) {
                HashSet<Character> set = new HashSet<>();
                for (int r = row; r < row + 3; r++) {
                    for (int c = col; c < col + 3; c++) {
                        
                        if (!sudokuMap.get(r).get(c).equals(' ') &&
                            set.add(sudokuMap.get(r).get(c)) == false) {
                            System.out.format("Block at (%d, %d) has multiple %c's!%n", 
                                            row, col, sudokuMap.get(r).get(c));
                            return false;
                        }
                    }
                }
            } 
        }
        return true;
    }
}  

