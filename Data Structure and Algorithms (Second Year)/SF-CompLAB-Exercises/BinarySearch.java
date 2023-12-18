// Binary Search Unmodified (LAB ACTIVITY 17)

// Import the Random class from the java.util package.
import java.util.Random;

// Import the Arrays class from the java.util package.
import java.util.Arrays;

// Import the Scanner class from the java.util package.
import java.util.Scanner;

// Start the definition of a class named Binary Search.
public class BinarySearch {

    // Start the main method, the entry point of the program.
    public static void main(String[] args) {

        // Declare and initialize an integer variable 'size' to 100.
        int size = 100;

        // Generate a random array of integers using the method generateRandomArray.
        int[] array = generateRandomArray(size);

        // Print a message indicating the initial unsorted array is being displayed.
        System.out.println("Initial unsorted array: ");

        // Display the contents of the generated array.
        displayArray(array);

        // Sort the array using the insertionSort method.
        insertionSort(array);

        // Print a message indicating the sorted array is being displayed.
        System.out.println("Sorted array: ");

        // Display the contents of the sorted array.
        displayArray(array);

        // Create a Scanner object to get user input.
        Scanner scanner = new Scanner(System.in);

        // Declare and initialize an integer variable 'target' to 0.
        int target = 0;

        // Declare a boolean variable 'validInput' and initialize it to false.
        boolean validInput = false;

        // Continue looping until a valid integer is entered by the user.
        while (!validInput) {

            // Prompt the user to enter an integer for searching.
            System.out.print("Enter an integer to search: ");

            // Check if the next input is an integer.
            if (scanner.hasNextInt()) {
                // If it's an integer, assign it to 'target' and set 'validInput' to true.
                target = scanner.nextInt();
                validInput = true;
            } else {
                // If it's not an integer, print an error message and consume the invalid input.
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consume the invalid input
            }
        }

        // Perform binary search on the sorted array and get the index of the target.
        int index = binarySearch(array, target);

        // Continue looping until the target is found in the array.
        while (index == -1) {
            // Print a message indicating the number is not found and prompt for another input.
            System.out.println("Number not found. Please enter another integer.");

            // Continue looping until a valid integer is entered by the user.
            while (!scanner.hasNextInt()) {
                // If it's not an integer, print an error message and consume the invalid input.
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consume the invalid input
            }

            // Assign the new input to 'target' and perform binary search again.
            target = scanner.nextInt();
            index = binarySearch(array, target);
        }

        // Print a message indicating the target is found along with its index.
        System.out.println("Number found at index: " + index);

        // Close the Scanner object to release resources.
        scanner.close();
    }

    // Method to generate a random array of the specified size.
    public static int[] generateRandomArray(int size) {
        // Create an integer array of the specified size.
        int[] array = new int[size];

        // Create a Random object for generating random numbers.
        Random random = new Random();

        // Fill the array with random integers in the range [-100, 100].
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(101);
        }

        // Return the generated array.
        return array;
    }

    // Method to display the contents of an array.
    public static void displayArray(int[] arr) {
        // Iterate through each element of the array and print it separated by commas.
        for (int num : arr) {
            System.out.print(num + ", ");
        }
        // Move to the next line after displaying the array.
        System.out.println();
    }

    // Method to perform insertion sort on an array.
    public static void insertionSort(int[] arr) {
        // Get the length of the input array.
        int n = arr.length;

        // Iterate through the array starting from the second element (index 1).
        for (int i = 1; i < n; i++) {
            // Store the current element at index 'i' in 'key'.
            int key = arr[i];
            // Initialize 'j' as the index of the element just before 'i'.
            int j = i - 1;

            // Move elements of arr[0..i-1] that are greater than 'key' to one position ahead of their current position.
            while (j >= 0 && arr[j] > key) {
                // Move the element at 'j' one position ahead.
                arr[j + 1] = arr[j];
                // Decrement 'j' to compare with the previous element.
                j--;
            }

            // Insert the 'key' into its correct position in the sorted portion of the array.
            arr[j + 1] = key;
        }
    }

    // Method to perform binary search on a sorted array.
    public static int binarySearch(int[] arr, int target) {
        // Call the helper method with initial boundaries.
        return binarySearchRecursive(arr, target, 0, arr.length - 1);
    }

    // Helper method for binary search using recursion.
    private static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        // Base case: If the target is not found, return -1.
        if (left > right) {
            return -1;
        }

        // Calculate the middle index.
        int mid = left + (right - left) / 2;

        // If the middle element is equal to the target, return the index.
        if (arr[mid] == target) {
            return mid;
        }
        // If the middle element is less than the target, search in the right half.
        else if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, right);
        }
        // If the middle element is greater than the target, search in the left half.
        else {
            return binarySearchRecursive(arr, target, left, mid - 1);
        }
    }
}