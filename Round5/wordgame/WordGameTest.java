
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordGameTest {
  public static void main(String args[]) throws IOException {
    String wordFilename = args[0];
    String cmdFilename = args[1];

    WordGame game = new WordGame(wordFilename);
    try(var cmdFile = new BufferedReader(new FileReader(cmdFilename))) {
      String cmd;
      while((cmd = cmdFile.readLine()) != null) {
        String[] parts = cmd.split("\\s+");
        try {
          WordGame.WordGameState state = null;
          switch(parts[0]) {
            case "init":
              System.out.format("Initializing a new game with %d and %d.%n",
                      Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
              game.initGame(Integer.parseInt(parts[1]), Integer.parseInt(
                      parts[2]));
              break;
            case "check":
              System.out.println("Checking game state.");
              System.out.format("  A game %s currently active.%n",
                      game.isGameActive() ? "is" : "is NOT");
              break;
            case "char":
              System.out.format("Guessing '%c'.%n", parts[1].charAt(0));
              state = game.guess(parts[1].charAt(0));
              break;
            case "word":
              System.out.format("Guessing \"%s\".%n", parts[1]);
              state = game.guess(parts[1]);
              break;
            case "state":
              System.out.println("Retrieving game status.");
              state = game.getGameState();
          }
          if(state != null) {
            System.out.format(
                    "  Word: %s (%d/%d wrong guesses, %d missing chars left)%n",
                    state.getWord(), state.getMistakes(), state
                    .getMistakeLimit(), state.getMissingChars());
            if(state.getMissingChars() == 0) {
              System.out.println("  The player won!");
            }
            else if(state.getMistakes() > state.getMistakeLimit()) {
              System.out.println("  The player lost!");
            }
          }
        }
        catch(GameStateException e) {
          System.out.println(e);
        }
      }
    }
  }

}
