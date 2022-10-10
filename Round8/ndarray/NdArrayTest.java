import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
public class NdArrayTest {
  private static void test1() {
    String[] pars = {"It", "must", "be", "simple", "object-oriented", "and",
                     "familiar", "It", "must", "be", "robust", "and", "secure",
                     "It", "must", "be", "architecture-neutral", "and",
                     "portable", "It", "must", "execute", "with", "high",
                     "performance", "It", "must", "be", "interpreted",
                     "threaded", "and", "dynamic"};
    try {
      new NdArray<String>(2, 3, 4, -1, 5);
    }
    catch(NegativeArraySizeException e) {
      System.out.println(e);
    }

    NdArray<String> strNdArr = new NdArray<>(2, 4, 4);
    int p = 0;
    int[] dims = strNdArr.getDimensions();
    for(int i = 0; i < dims[0]; i++) {
      for(int j = 0; j < dims[1]; j++) {
        for(int k = 0; k < dims[2]; k++) {
          strNdArr.set(pars[p++], i, j, k);
        }
      }
    }

    try {
      strNdArr.set("item", 1, 2);
    }
    catch(IllegalArgumentException e) {
      System.out.println(e);
    }

    try {
      strNdArr.set("item", 1, 4, 3);
    }
    catch(IndexOutOfBoundsException e) {
      System.out.println(e);
    }

    System.out.println();
    for(int i = 0; i < dims[0]; i++) {
      for(int j = 0; j < dims[1]; j++) {
        for(int k = 0; k < dims[2]; k++) {
          System.out
                  .format(" (%d,%d,%d): %s", i, j, k, strNdArr.get(i, j, k));
        }
        System.out.println();
      }
    }

    for(String s : strNdArr) {
      if("It".equals(s)) {
        System.out.println();
      }
      System.out.print(" " + s);
    }
    System.out.println();
    System.out.println();

    List<String> len4s = strNdArr.stream().filter(w -> w.length() == 4).collect(
            Collectors.toList());
    TreeSet<String> len4Set = new TreeSet<>(len4s);
    System.out.println(len4Set);
  }

  private static void test2() {
    int[] pars = {-49, -42, -9, 23, -66, -97, -30, 51, 30, -70, -60, 50, 9, -51,
                  36, 24, 39, 30, 34, -65, -84, -30, 33, 21};
    NdArray<Integer> intNdArr = new NdArray<>(3, 2, 4);
    int p = 0;
    int[] dims = intNdArr.getDimensions();
    for(int i = 0; i < dims[0]; i++) {
      for(int j = 0; j < dims[1]; j++) {
        for(int k = 0; k < dims[2]; k++) {
          intNdArr.set(pars[p++], i, j, k);
        }
      }
    }

    for(int i = 0; i < dims[0]; i++) {
      for(int j = 0; j < dims[1]; j++) {
        for(int k = 0; k < dims[2]; k++) {
          System.out
                  .format(" (%d,%d,%d): %d", i, j, k, intNdArr.get(i, j, k));
        }
        System.out.println();
      }
    }
    System.out.println();

    int min = intNdArr.stream().mapToInt(i -> i).min().getAsInt();
    System.out.println("The minimum value is: " + min);
  }

  private static void test3() {
    double[] pars = {-4.50, 3.00, -8.50, 1.90, -2.10, -7.60, 6.40, -4.00, 1.70,
                     -5.60, -1.50, 1.70, -1.90, 1.30, 1.10, 6.90, -1.40, -6.90,
                     -1.10, -3.40, 1.10, -5.10, -1.40, -1.30, -1.30, -4.80, 7.90,
                     2.30, -1.00, -1.10};
    NdArray<Double> dblNdArr = new NdArray<>(2, 3, 5);
    int p = 0;
    int[] dims = dblNdArr.getDimensions();
    for(int i = 0; i < dims[0]; i++) {
      for(int j = 0; j < dims[1]; j++) {
        for(int k = 0; k < dims[2]; k++) {
          dblNdArr.set(pars[p++], i, j, k);
        }
      }
    }

    for(int i = 0; i < dims[0]; i++) {
      for(int j = 0; j < dims[1]; j++) {
        for(int k = 0; k < dims[2]; k++) {
          System.out.format(" (%d,%d,%d): %.1f", i, j, k, dblNdArr.get(i, j, k));
        }
        System.out.println();
      }
    }
    System.out.println();

    DoubleSummaryStatistics stats = dblNdArr.stream().mapToDouble(i -> i)
            .summaryStatistics();
    System.out.format("Summary statistics:%n%s%n", stats);
  }

  private static void test4() {
    NdArray<Integer> intNdArr = new NdArray<>(2, 5, 4, 3, 2, 7, 4, 5);
    int val = 5;
    int[] dims = intNdArr.getDimensions();
    for(int i = 0; i < dims[0]; i++) {
      for(int j = 0; j < dims[1]; j++) {
        for(int k = 0; k < dims[2]; k++) {
          for(int l = 0; l < dims[3]; l++) {
            for(int m = 0; m < dims[4]; m++) {
              for(int n = 0; n < dims[5]; n++) {
                for(int o = 0; o < dims[6]; o++) {
                  for(int p = 0; p < dims[7]; p++) {
                    intNdArr.set(val, i, j, k, l, m, n, o, p);
                    val = (13 * (val + i + j + k + l + m + n + o + p) + 7) % 991;
                  }
                }
              }
            }
          }
        }
      }
    }
    int sum = 0;
    for(int i = 0; i < dims[0]; i++) {
      for(int j = 0; j < dims[1]; j++) {
        for(int k = 0; k < dims[2]; k++) {
          for(int l = 0; l < dims[3]; l++) {
            for(int m = 0; m < dims[4]; m++) {
              for(int n = 0; n < dims[5]; n++) {
                for(int o = 0; o < dims[6]; o++) {
                  for(int p = 0; p < dims[7]; p++) {
                    sum += intNdArr.get(i, j, k, l, m, n, o, p);
                  }
                }
              }
            }
          }
        }
      }
    }
    System.out.println("Self computed sum: " + sum);
    IntSummaryStatistics stats = intNdArr.stream().mapToInt(i -> i)
            .summaryStatistics();
    System.out.format("Summary statistics:%n%s%n", stats);
  }

  public static void main(String[] args) {
    switch(args[0]) {
      case "4":
        test4();
        break;
      case "3":
        test3();
        break;
      case "2":
        test2();
        break;
      case "1":
      default:
        test1();
    }
  }

}
