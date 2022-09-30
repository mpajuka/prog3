import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Standings {
    private HashMap<String, Team> teamMap = new HashMap<>();

    public Standings() {
    }

    public Standings(String filename) throws IOException {
        readMatchData(filename);
    }

    public static class Team {
        private String name;
        private int playedGames;
        private int wins;
        private int ties;
        private int losses;
        private int scored;
        private int allowed;
        private int points;

        public Team(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getWins() {
            return wins;
        }

        public int getTies() {
            return ties;
        }

        public int getLosses() {
            return losses;
        }

        public int getScored() {
            return scored;
        }

        public int getAllowed() {
            return allowed;
        }

        public int getPoints() {
            return points;
        }
    }

    public void printStandings() {
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
            int numlength = 4;
            if (team.allowed > 9) {
                numlength = 3;
            } else if (team.allowed > 99) {
                numlength = 2;
            }
            System.out.format("%-"+teamNameLength+"s%4d %3d %3d %3d %"+numlength+"d-%d %3d%n",
            team.name, team.playedGames, team.wins, team.ties, team.losses,
            team.scored, team.allowed, team.points);
            
        }
        
    }

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

    public ArrayList<Team> getTeams() {
        ArrayList<Team> teamReturn = new ArrayList<>(teamMap.values());

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
