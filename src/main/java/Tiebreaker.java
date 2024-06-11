import java.util.HashMap;

public class Tiebreaker extends Player{

    Tiebreaker(String name) {
        super(name);
    }

    @Override
    void increaseScore() {
        System.out.println("Hello There :)");
    }

    @Override
    String getName() {
        return "TieBreaker";
    }

    @Override
    int getScore() {
        return -1;
    }

    @Override
    String makeChoice(HashMap<String, String> options) {
        return "Hello I am too indecisive to make a move";
    }
}
