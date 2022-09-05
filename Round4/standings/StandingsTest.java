
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class StandingsTest {
  public static void main(String[] args)
          throws FileNotFoundException, IOException {
    int dice = new Random().nextInt();
    Standings standings;
    if(dice % 2 == 0) {
      standings = new Standings(args[0]);
    }
    else {
      standings = new Standings();
      standings.readMatchData(args[0]);
    }
    System.out.println("Initial standings:");
    standings.printStandings();

    for(int i = 2; i + 3 < args.length; i += 4) {
      standings.addMatchResult(args[i], Integer.parseInt(args[i + 1]),
              Integer.parseInt(args[i + 2]), args[i + 3]);
    }
    System.out.format("%nTeam data in standing order after added matches:%n");
    for(Standings.Team team : standings.getTeams()) {
      System.out.format("%s %d %d %d %d %d %d%n", team.getName(),
              team.getWins(), team.getTies(), team.getLosses(),
              team.getScored(), team.getAllowed(), team.getPoints());
    }

    standings.readMatchData(args[1]);
    System.out.format("%nTeam standings after reading even more matches:%n");
    standings.printStandings();
  }

}
