import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class LABExerciseNine {
    private ArrayList<String> array = new ArrayList<>();

    public static void main(String[] args) {
        LABExerciseNine stringList = new LABExerciseNine();
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Load data from a file (if it exists)
        loadFromFile(stringList);

        while (true) {
            printMenu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addElement(stringList, scanner);
                    break;
                case 2:
                    displayElements(stringList);
                    break;
                case 3:
                    findElement(stringList, scanner);
                    break;
                case 4:
                    saveAndExit(stringList);
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Add Data to List");
        System.out.println("2. Display All Data");
        System.out.println("3. Find Data");
        System.out.println("4. Save and Exit");
    }

    private static void addElement(LABExerciseNine stringList, Scanner scanner) {
        System.out.println("Enter an element to add to the list: ");
        String element = scanner.next();
        stringList.array.add(element);
        System.out.println("Element added.");
    }

    private static void displayElements(LABExerciseNine stringList) {
        for (int i = 0; i < stringList.array.size(); i++) {
            System.out.println("Element " + i + ": " + stringList.array.get(i));
        }
    }

    private static void findElement(LABExerciseNine stringList, Scanner scanner) {
        System.out.println("Enter an element to find: ");
        String target = scanner.next();
        int index = stringList.array.indexOf(target);
        if (index != -1) {
            System.out.println("Element found at index: " + index);
        } else {
            System.out.println("Element not found.");
        }
    }

    private static void loadFromFile(LABExerciseNine stringList) {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                stringList.array.add(line);
            }
            System.out.println("Data loaded from file.");
        } catch (IOException e) {
            System.out.println("No saved data found.");
        }
    }

    private static void saveAndExit(LABExerciseNine stringList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt"))) {
            for (String element : stringList.array) {
                bw.write(element);
                bw.newLine();
            }
            System.out.println("Data saved. Goodbye!");
        } catch (IOException e) {
            System.out.println("Error while saving data.");
        }
    }
}
