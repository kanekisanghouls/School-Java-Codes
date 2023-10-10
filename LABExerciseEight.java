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
        System.out.println("");
        System.out.println("Menu:");
        System.out.println("1. Add Data to List");
        System.out.println("2. Display All Data");;
        System.out.println("3. Exit");
        choice = scanner.nextInt();

        while (choice != 3) {
            if (choice == 1) {
                System.out.println("Enter an element to add to the list: ");
                String element = scanner.next();
                stringList.add(element);
            } else if (choice == 2) {
                stringList.display();
            } else {
                System.out.println("Invalid choice!");
            }

            System.out.println("\n");
            System.out.println("Menu:");
            System.out.println("1. Add Data to List");
            System.out.println("2. Display All Data");;
            System.out.println("3. Exit");

            choice = scanner.nextInt();
        }

        System.out.println("Program Terminated.");
    }
}
