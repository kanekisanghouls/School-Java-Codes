import java.util.Scanner;
public class EggStore {
    public static void main(String[] args) {
        System.out.print("\n[Title of your Store]");
        System.out.println("\nPlease enter the number of eggs, \nyou would like to order: ");
        Scanner scanner = new Scanner(System.in);
        int numberEggs = scanner.nextInt(); scanner.close();
        int numberDozens = numberEggs / 12; int numberSingleEggs = numberEggs % 12;
        double totalCost = numberDozens * 3.25 + numberSingleEggs * 0.45;

        System.out.println("You ordered " + numberEggs + " eggs.");
        System.out.println("That's " + numberDozens + " dozen at $3.25 per dozen and "
        + numberSingleEggs + " loose eggs at 45 cents each for a total of $" + totalCost);
    }
    /*  / - division
     *  % - modulo
     *  * - multiplication
     *  + - addition
    */
    
}
