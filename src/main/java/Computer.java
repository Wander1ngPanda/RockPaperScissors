import java.util.HashMap;
import java.util.Random;


public class Computer extends Player{

    Computer(String name) {
        super(name);
    }

    @Override
    String makeChoice(HashMap<String, String> options) {
        Random randomizer = new Random();
        return options.keySet().toArray()[randomizer.nextInt(options.size())].toString();
    }

    @Override
    String getName() {
        return this.name;
    }
}
