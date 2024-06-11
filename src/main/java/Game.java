import java.util.*;

public class Game {
    private Player player1;
    private Player player2;

    //Game Options - Key Is Choice Value is what that choice beats
    private final HashMap<String, String> gameOptions = new HashMap<>(Map.of(
            "rock", "scissors",
            "scissors", "paper",
            "paper","rock"
    ));

    //Game Loop
    public void gameMainLoop(){
        Player winner;
        playerSelection();
        System.out.println("The players are:");
        System.out.println("Player 1: " + player1.getName());
        System.out.println("Player 2: " + player2.getName());
        while (true) {
            System.out.println("--------------------");
            game(this.player1, this.player2);
            System.out.println("--------------------\nWould you like to play again? (y/n)");
            if (Main.inputFixerVisible("Please choose y or n").equals("y")){
                continue;
            }
            winner = getWinner(player1, player2);
            break;
        }

        if (winner.getClass().equals(Tiebreaker.class)){
            System.out.println("--------------------\nIts a Tie!!!\nThat's not allowed.\nTime for a TieBreak\nWinner Takes All!!!");
            game(this.player1, this.player2);
        } else {
            System.out.println(winner.getName() + " is the Winner with " + winner.getScore() +" points!!!");
        }
        System.out.println("\nThanks for playing!!!");
    }

    //Game Logic
    private void game(Player player1, Player player2){
        String choice1;
        String choice2;
        while (true){
            choice1 = player1.makeChoice(gameOptions);
            choice2 = player2.makeChoice(gameOptions);
            System.out.println(player1.getName() + " threw " + choice1);
            System.out.println(player2.getName() + " threw " + choice2);
            if (!choice1.equals(choice2)){
                break;
            }
            System.out.println("The game is a tie. Lets play again!!!\n--------------------");
        }

        if (victoryChecker(choice1, choice2)){
            player1.increaseScore();
            System.out.println(player1.getName() + " Wins this Round\n--------------------");
        } else {
            player2.increaseScore();
            System.out.println(player2.getName() + " Wins this Round\n--------------------");
        }
        System.out.println("The current score is:");
        System.out.println(player1.getName() + " :" + player1.getScore());
        System.out.println(player2.getName() + " :" + player2.getScore());
    }

    //Check Winner
    private boolean victoryChecker(String choice1, String choice2) {
        try {
        if (choice1.equals(choice2)){
            throw new SameChoiceException("The same value has been entered into the Victory Checker when it should receive 2 different values!");
        }} catch (SameChoiceException sce){
            sce.printStackTrace();
        }
        String choice1Loser = gameOptions.get(choice1);
        return choice1Loser.equals(choice2);

    }

    private static class SameChoiceException extends Exception{
        public SameChoiceException(String message){
            super(message);
        }
    }

    private void setPlayer(boolean isHuman){
        String name;
        if (isHuman){
            name = Main.inputFixerVisible("Please enter a Player name:");
        } else {
            name = Main.inputFixerVisible("Please enter a Computer name:");
        }
        if (this.player1 == null){
            if (isHuman){
                this.player1 = new Human(name);
            } else {
                this.player1 = new Computer(name);
            }
        } else {
            if (isHuman){
                this.player2 = new Human(name);
            } else {
                this.player2 = new Computer(name);
            }
        }
    }

    private void playerSelection(){
        System.out.println("Do you want to play:\n1. Player vs Computer\n2. Player vs Player\n3. Computer vs Computer");
        List<Integer> options = new ArrayList<>(Arrays.asList(1, 2, 3));
        int input;
        while (true){

            try{
                input = Integer.parseInt(Main.inputFixerVisible("Please enter a number"));
            }catch (NumberFormatException nfe){
                System.out.println("That is not a valid number\nPlease try again");
                continue;
            }catch (Exception e){
                e.printStackTrace();
                input = -1;
            }

            if (options.contains(input)){
                break;
            }
            System.out.println("That is not a valid option.\nPlease enter 1, 2 or 3.");
        }
        if (input == 1){
            setPlayer(true);
            setPlayer(false);
        } else if (input == 2) {
            setPlayer(true);
            setPlayer(true);
        } else {
            setPlayer(false);
            setPlayer(false);
        }
    }

    private Player getWinner(Player player1, Player player2){
        if (player1.getScore() > player2.getScore()){
            return player1;
        } else if (player1.getScore() < player2.getScore()){
            return player2;
        }
        else {
            return new Tiebreaker("TieBreak");
        }
    }

}
