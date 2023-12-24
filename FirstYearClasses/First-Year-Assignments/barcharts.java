import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
public class BarChart{
    public static void main(String [] args){ 
        Map<String, Integer> listOfScores = new LinkedHashMap<>();
        Scanner input = new Scanner(System.in);
        // Get each name from the list of players
        for(String name : nameOfPlayers()){
            System.out.print("Enter points scored by " + name + " >> ");
            listOfScores.put(name, input.nextInt());
        }
        System.out.println("\nPoints for Game:\n");
        // Display the convert from numbers to asterisk
        for (Map.Entry<String, Integer> entry : listOfScores.entrySet()) {
            System.out.println(entry.getKey() + "\t" + setNumbersAsAsterisk(entry.getValue()));
        }
    }
    // Add the List of Players
    private static ArrayList<String> nameOfPlayers(){
        ArrayList<String> names = new ArrayList<String>();
        names.add("");
        names.add("");
        names.add("");
        names.add("");
        names.add("");
        return names;
    }
    // Get the Scores and turn it to Asterisk
    private static String setNumbersAsAsterisk(int score){
        String lineOfAsterisk = "";
        for (int i = 0; i < score; i++) {
            lineOfAsterisk = lineOfAsterisk + "*";
        }
        return lineOfAsterisk;
    }
}
