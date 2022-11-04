package fi.tuni.prog3.standings;

import java.io.PrintStream;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 *  A class for maintaining team statistics and standings. Team standings are 
 *  determined by the following rules:
 * <ul>
 *     <li>Primary rule: points total. Higher points come first.</li>
 *     <li>Secondary rule: goal difference (scored minus allowed). Higher difference comes first.</li>
 *     <li>Tertiary rule: number of goals scored. Higher number comes first.</li>
 *     <li>Last rule: natural String order of team names.</li>
 * </ul>
 */
public class Standings {
    private HashMap<String, Team> teamMap = new HashMap<>();
    /**
     * Constructs an empty Standings object.
     */
    public Standings() {
    }
    /**
     * Constructs a Standings object that is initialized with the game data
     * read from the specified file. The result is identical to first constructing
     * an empty Standing object and then calling
     * {@link  #readMatchData(String) readMatchData(filename)}.
      * @param filename     the name of the game data file to read.
     * @throws IOException  if there is some kind of an IO error (e.g. if the 
     * specified file does not exist).
     */
    public Standings(String filename) throws IOException {
        readMatchData(filename);
    }

   /**
     * A class for storing statistics of a single team. The class offers only 
     * public getter functions. The enclosing class Standings is responsible for
     * setting and updating team statistics.
     */
    public static class Team {
        private String name;
        private int playedGames;
        private int wins;
        private int ties;
        private int losses;
        private int scored;
        private int allowed;
        private int points;

        /**
         * Constructs a Team object for storing statistics of the named team.   
         * @param name the name of the team whose statistics the new team object
         * stores.
         */
        public Team(String name) {
            this.name = name;
        }

        /**
         * Returns team name
         * @return team name
         */
        public String getName() {
            return name;
        }

        /**
         * Return amount of wins
         * @return amount of wins
         */
        public int getWins() {
            return wins;
        }

        /**
         * Return amount of ties
         * @return amount of ties
         */
        public int getTies() {
            return ties;
        }

        /**
         * Return amount of losses
         * @return amount of losses
         */
        public int getLosses() {
            return losses;
        }

        /**
         * Return amount of scored goals
         * @return amount of scored goals
         */
        public int getScored() {
            return scored;
        }

        /**
         * Return amount of allowed goals
         * @return amount of allowed goals
         */
        public int getAllowed() {
            return allowed;
        }

        /**
         * Return amount of points
         * @return amount of points
         */
        public int getPoints() {
            return points;
        }

        /**
         * Return amount of played games
         * @return amount of played games
         */
        public int getPlayedGames() {
            return playedGames;
        }
    }
    /**
     * Reads game data from the specified file and updates the team
     * statistics and standings accordingly.
     *
     * <p>The match data file is expected to contain lines of form
     * "teamNameA\tgoalsA-goalsB\tteamNameB". Note that the '\t' are tabulator 
     * characters.</p>
     *
     * <p>E.g. the line "Iceland\t3-2\tFinland" would describe a match between
     * Iceland and Finland where Iceland scored 3 and Finland 2 goals.</p>
     *
     * @param filename      the name of the game data file to read.
     * @throws IOException  if there is some kind of an IO error (e.g. if the 
     * specified file does not exist).
     */
    public final void readMatchData(String filename) throws IOException{
        try(var file = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = file.readLine()) != null) {
                String[] parts = line.split("\\t");
                String teamA = parts[0];
                String teamB = parts[2];

                String[] scores = parts[1].split("-");
                String goalsA = scores[0];
                String goalsB = scores[1];

                int tgoalsA = Integer.parseInt(goalsA);
                int tgoalsB = Integer.parseInt(goalsB);

                addMatchResult(teamA, tgoalsA, tgoalsB, teamB);
            }
        }
    }
    
    /**
     * Updates the team statistics and standings according to the match
     * result described by the parameters.
     * @param teamNameA     the name of the first ("home") team.
     * @param goalsA        the number of goals scored by the first team.
     * @param goalsB        the number of goals scored by the second team.
     * @param teamNameB     the name of the second ("away") team.
     */
    public void addMatchResult(String teamNameA, int goalsA, int goalsB, String teamNameB) {
        Team teamA = teamMap.computeIfAbsent(teamNameA, tn-> new Team(teamNameA));
        Team teamB = teamMap.computeIfAbsent(teamNameB, tn-> new Team(teamNameB));

        teamA.playedGames += 1;
        teamB.playedGames += 1;

        teamA.scored += goalsA;
        teamA.allowed += goalsB;

        teamB.scored += goalsB;
        teamB.allowed += goalsA;

        if (goalsA > goalsB) {
            teamA.wins += 1;
            teamA.points += 3;
            teamB.losses += 1;
        } else if (goalsB > goalsA) {
            teamB.wins += 1;
            teamB.points += 3;
            teamA.losses += 1;
        } else {
            teamA.ties += 1;
            teamA.points += 1;
            teamB.ties += 1;
            teamB.points += 1;

        }

    }

    
    /**
     * Returns a list of the teams in the same order as they would appear in a standings table.
     * @return  a list of the teams in the same order as they would appear in a standings table.
     */
    public List<Team> getTeams() {
        List<Team> teamReturn = new ArrayList<>(teamMap.values());

        Comparator<Team> sortByName = (a, b) -> b.name.compareTo(a.name);
        teamReturn.sort(sortByName);

        Comparator<Team> sortByGoals = (a, b) -> b.scored - a.scored;
        teamReturn.sort(sortByGoals);

        Comparator<Team> sortByDiff = (a, b) -> (b.scored-b.allowed) - (a.scored-a.allowed);
        teamReturn.sort(sortByDiff);

        Comparator<Team> sortByPoints = (a, b) -> b.points - a.points;
        teamReturn.sort(sortByPoints);

        return teamReturn;
    }
    
    
    /**
     * Prints a formatted standings table to the provided output stream.
     * @param out the output stream to use when printing the standings table.
     */
    public void printStandings(PrintStream out) {
        ArrayList<String> teamNames = new ArrayList<>(teamMap.keySet());
        int teamNameLength = 0;

        for (String team : teamNames) {
            if (team.length() > teamNameLength) {
                teamNameLength = team.length();
            }
        }


        ArrayList<Team> teamSorted = new ArrayList<>(teamMap.values());

        Comparator<Team> sortByName = (a, b) -> b.name.compareTo(a.name);
        teamSorted.sort(sortByName);

        Comparator<Team> sortByGoals = (a, b) -> b.scored - a.scored;
        teamSorted.sort(sortByGoals);

        Comparator<Team> sortByDiff = (a, b) -> (b.scored-b.allowed) - (a.scored-a.allowed);
        teamSorted.sort(sortByDiff);

        Comparator<Team> sortByPoints = (a, b) -> b.points - a.points;
        teamSorted.sort(sortByPoints);

        for (Team team : teamSorted) {
            out.printf("%-" + teamNameLength + "s", team.getName());
            out.printf("%4s", team.getPlayedGames());
            out.printf("%4s", team.getWins());
            out.printf("%4s", team.getTies());
            out.printf("%4s", team.getLosses());
            out.printf("%7s", team.getScored() + "-" + team.getAllowed());
            out.printf("%4s%n", team.getPoints());
        }

    }

    
    

    
}
