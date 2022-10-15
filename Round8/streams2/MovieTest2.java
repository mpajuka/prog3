import java.io.IOException;
public class MovieTest2 {
  public static void main(String[] args)
          throws IOException {
    MovieAnalytics2 ma = new MovieAnalytics2();
    ma.populateWithData(args[0]);
    int n = Integer.parseInt(args[1]);

    System.out.format("Top %d directors with highest number of movies:%n", n);
    ma.printCountByDirector(n);

    System.out.println();
    System.out.println("Average duration by genre:");
    ma.printAverageDurationByGenre();

    System.out.println();
    System.out.println("Average score by genre:");
    ma.printAverageScoreByGenre();
  }

}
