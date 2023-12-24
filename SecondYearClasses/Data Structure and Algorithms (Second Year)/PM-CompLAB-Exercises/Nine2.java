import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Nine2 {

    private String[] array;
    private int size;

    public Nine2() {
        this.array = new String[10];
        this.size = 0;
        load();
    }

    public void add(String element) {
        if (size == array.length) {
            String[] newArray = new String[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[size] = element;
        size++;
    }

    public void display() {
        System.out.println("String List:");
        for (int i = 0; i < size; i++) {
            System.out.println("[" + i + "] " + array[i]);
        }
    }

    public int search(String element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public void save() {
        try (FileWriter writer = new FileWriter("stringlist.txt")) {
            for (int i = 0; i < size; i++) {
                writer.write(array[i] + "\n");
            }
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader("stringlist.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                add(line);
            }
            System.out.println("Data loaded from file.");
        } catch (IOException e) {
            System.err.println("Error loading data from file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Nine2 stringList = new Nine2();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Menu:");
        System.out.println("1. Add Item to List");
        System.out.println("2. Display All Items");
        System.out.println("3. Search for Item in List");
        System.out.println("4. Exit");

        int choice = 0;

        while (choice != 4) {
            try {
                choice = scanner.nextInt();
                if (choice == 1) {
                    System.out.println("Enter an element(Add List): ");
                    String element = scanner.next();
                    stringList.add(element);
                } else if (choice == 2) {
                    stringList.display();
                } else if (choice == 3) {
                    System.out.println("Enter an element to search for: ");
                    String element = scanner.next();
                    int index = stringList.search(element);
                    if (index != -1) {
                        System.out.println("The element \"" + element + "\" is found at index " + index + ".");
                    } else {
                        System.out.println("The element \"" + element + "\" is not found in the list.");
                    }
                } else if (choice != 4) {
                    System.out.println("Invalid choice!");
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a valid option (1, 2, 3, or 4).");
                scanner.nextLine(); // Consume the invalid input to avoid an infinite loop
            }
        }

        stringList.save();
        System.out.println("Goodbye!");
    }
}
