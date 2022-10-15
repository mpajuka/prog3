import java.io.IOException;
import java.util.function.Consumer;
import java.util.stream.Stream;
public class MovieTest {
  public static void main(String[] args)
          throws IOException {
    final Consumer<Movie> showInfo = MovieAnalytics.showInfo();
    Stream<Movie> movies;

    MovieAnalytics ma = new MovieAnalytics();
    ma.populateWithData(args[0]);

    System.out.println("Movies after 2020:");
    movies = ma.moviesAfter(2020);
    movies.forEach(showInfo);

    System.out.println();
    System.out.println("Movies before 1930:");
    movies = ma.moviesBefore(1930);
    movies.forEach(showInfo);

    System.out.println();
    System.out.println("Movies between 1971-1972:");
    movies = ma.moviesBetween(1971, 1972);
    movies.forEach(showInfo);

    System.out.println();
    System.out.println("Movies by Martin Scorsese:");
    movies = ma.moviesByDirector("Martin Scorsese");
    movies.forEach(showInfo);
  }

}
