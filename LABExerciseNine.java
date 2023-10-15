import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class LABExerciseNine {
    private String[] array;

    public LABExerciseNine() {
        this.array = new String[0];
    }

    public void add(String element) {
        String[] newArray = new String[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[array.length] = element;
        array = newArray;
    }

    public void display() {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Element " + i + ": " + array[i]);
        }
    }

    public int find(String target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(target)) {
                return i;
            }
        }
        return -1; // Not found
    }

    public static void main(String[] args) {
        LABExerciseNine stringList = new LABExerciseNine();
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Load data from a file (if it exists)
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                stringList.add(line);
            }
        } catch (IOException e) {
            System.out.println("No saved data found.");
        }

        System.out.println("Menu:");
        System.out.println("1. Add Data to List");
        System.out.println("2. Display All Data");
        System.out.println("3. Find Data");
        System.out.println("4. Save and Exit");
        choice = scanner.nextInt();

        while (choice != 4) {
            if (choice == 1) {
                System.out.println("Enter an element to add to the list: ");
                String element = scanner.next();
                stringList.add(element);
            } else if (choice == 2) {
                stringList.display();
            } else if (choice == 3) {
                System.out.println("Enter an element to find: ");
                String target = scanner.next();
                int index = stringList.find(target);
                if (index != -1) {
                    System.out.println("Element found at index: " + index);
                } else {
                    System.out.println("Element not found.");
                }
            } else {
                System.out.println("Invalid choice!");
            }

            System.out.println("\nMenu:");
            System.out.println("1. Add Data to List");
            System.out.println("2. Display All Data");
            System.out.println("3. Find Data");
            System.out.println("4. Save and Exit");

            choice = scanner.nextInt();
        }

        // Save the data to a file upon exit
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt"))) {
            for (String element : stringList.array) {
                bw.write(element);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error while saving data.");
        }

        System.out.println("Data saved. Goodbye!");
    }
}
