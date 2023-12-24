// Modified but I forgot to save my complete code.

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

public class Data {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nMENU");
            System.out.println("1. Add Data");
            System.out.println("2. Display Data");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addData();
                    break;
                case 2:
                    displayData();
                    break;
                case 3:
                    System.out.println("Bye bye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option. (1-3)");
            }
        }
    }

    private static void addData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID No.: ");
        String idno = sc.next();
        System.out.print("Enter Last Name: ");
        String lastname = sc.next();
        System.out.print("Enter First Name.: ");
        String firstname = sc.next();

        try {
            // Creates a FileWriter
            FileWriter file = new FileWriter("manipulate.txt", true);
            // Creates a BufferedWriter
            BufferedWriter output = new BufferedWriter(file);
            // Writes the string to the file
            output.write(idno + " ");
            output.write(lastname + " ");
            output.write(firstname);
            output.newLine();
            // Closes the writer
            output.close();
            System.out.println("Data added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

private static void displayData() {
    try {
        String line;
        // Creates a FileReader
        FileReader file = new FileReader("manipulate.txt");
        // Creates a BufferedReader
        BufferedReader input = new BufferedReader(file);

        System.out.println("\nDISPLAY DATA");
        System.out.printf("%-10s%-20s%-20s%n", "ID No.", "Last Name", "First Name");

        while ((line = input.readLine()) != null) {
            String[] data = line.split(" ");
            System.out.printf("%-10s%-20s%-20s%n", data[0], data[1], data[2]);
        }
        
        input.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
