import java.util.HashMap;

public class Human extends Player{
    Human(String name) {
        super(name);
    }

    @Override
    String makeChoice(HashMap<String, String> options) {
        HashMap<String, String> simplifiedOptions = Main.simpleObjectMapGen(options);
        System.out.println(this.name + ": What would you like to throw?");
        for (String item:
             options.keySet()) {
            System.out.println(item.charAt(0) + " :" + item);
        }
        System.out.println();
        String input;
        while (true){
            try{
            input = Main.inputFixerInvisible();
            } catch (NullPointerException npe){
                input = Main.inputFixerVisible("Please choose one of the options");
            }
            if (options.containsKey(input)){
                break;
            } else if (simplifiedOptions.containsKey(input)) {
                input = simplifiedOptions.get(input);
                break;
            }
            System.out.println("That is not a valid choice.\nPlease Choose again!");
        }
        return input;
    }

    @Override
    String getName() {
        return this.name;
    }
}
