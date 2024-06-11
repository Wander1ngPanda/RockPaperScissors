import java.util.HashMap;

public abstract class Player {

    public String name;
    private int score = 0;

    Player(String name){
        this.name = name;
    }

    abstract String makeChoice(HashMap<String, String> options);

    abstract String getName();

    void increaseScore(){
        this.score++;
    }

    int getScore() {
        return score;
    }
}
