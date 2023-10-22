import java.util.Scanner;
import java.util.InputMismatchException;
public class LABExerciseEight {
    private String[] array;
    public LABExerciseEight() {
        this.array = new String[0];
    }
    public void add(String element) {
        // Create a new array that is bigger than the old array
        String[] newArray = new String[array.length + 1];
        // Copy the old array to the new array
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        // Add the new element to the new array
        newArray[array.length] = element;
        // Set the old array to the new array
        array = newArray;
    }
    public void display() {
        for (String element : array) {
            System.out.println(element);
        }
    }
    public static void main(String[] args) {
        LABExerciseEight stringList = new LABExerciseEight();
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("\nMenu:");
        System.out.println("1. Add Data to List");
        System.out.println("2. Display All Data");
        System.out.println("3. Exit");
        choice = getChoice(scanner);
        while (choice != 3) {
            if (choice == 1) {
                System.out.println("Enter an element(Add List): ");
                String element = scanner.next();
                stringList.add(element);
            } else if (choice == 2) {
                stringList.display();
            } else {
                System.out.println("Invalid choice!");
            }
            System.out.println("\nMenu:");
            System.out.println("1. Add Data to List");
            System.out.println("2. Display All Data");
            System.out.println("3. Exit");
            choice = getChoice(scanner);
        }
        System.out.println("Program Terminated.");
    }
    private static int getChoice(Scanner scanner) {
        int choice = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                choice = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option (1, 2, or 3).");
                scanner.next(); // Clear the invalid input
            }
        }
        return choice;
    }
}
