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
     * Empty Standings object.
     */
    public Standings() {
    }
    /**
     *
     * {@link #readMatchData(String) readMatchData(fileName)}
     * @param filename  filename to read game data from
     * @throws IOException  if file doesn't exist
     */
    public Standings(String filename) throws IOException {
        readMatchData(filename);
    }

    /**
     * Class for storing all the info for a specific team
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
         * Constructs Team object to store match data
         * @param name the name of the team
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
     * Prints team table according to rules
     * @param p output stream for printing table
     */
    public void printStandings(PrintStream p) {
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
            p.printf("%-" + teamNameLength + "s", team.getName());
            p.printf("%4s", team.getPlayedGames());
            p.printf("%4s", team.getWins());
            p.printf("%4s", team.getTies());
            p.printf("%4s", team.getLosses());
            p.printf("%7s", team.getScored() + "-" + team.getAllowed());
            p.printf("%4s%n", team.getPoints());
        }

    }

    /**
     * Adds results to each team according to file input
     * @param teamNameA name of team a
     * @param goalsA    goals of team a
     * @param goalsB    goals of team b
     * @param teamNameB name of team b
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
     * Reads data from text file and parses it to be read into the addMatch
     * Result function
     * @param filename      name of file to be read
     * @throws IOException  file doesn't exist
     */
    public void readMatchData(String filename) throws IOException{
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
     * Returns list of teams sorted based on the predetermined rules
     * @return a list sorted based on the rules
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
}
