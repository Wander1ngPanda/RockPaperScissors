import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Note: For a better PvP experience with hidden inputs play in the CLI\n--------------------");
        System.out.println("Lets Play: Rock Paper Scissors");
        Game rockPaperScissors = new Game();
        rockPaperScissors.gameMainLoop();
    }

    //Helper method to avoid empty strings and process inputs smoothly
    static String inputFixerVisible(String message){
        String input;
        while (true) {
            System.out.println(message);
            input = scanner.nextLine();
            if (input.trim().isEmpty()){
                continue;
            }
            break;
        }
        return input;
    }


    //Invisible relies on System.console() which is unavailable in IDEs
    //This has been handled where it is used. It enables hidden inputs
    static String inputFixerInvisible(){
        String input;
        while (true) {
            System.console().printf("Please choose one of the options" + "\n");
            input = String.valueOf(System.console().readPassword());
            if (input.trim().isEmpty()){
                continue;
            }
            break;
        }
        return input;
    }

    //Helper method to process a hashMap to produce a hashmap relating the first char of key to the key
    static HashMap<String, String> simpleObjectMapGen(HashMap<String, String> hashMap){
        HashMap<String, String> output = new HashMap<>();
        for (String key:
                hashMap.keySet()) {
            output.put(String.valueOf(key.charAt(0)), key);
        }
        return output;
    }
}
