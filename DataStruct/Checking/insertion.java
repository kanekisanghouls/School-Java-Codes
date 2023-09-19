import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
public class insertion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size;
        int[] originalArray;
        for (;;) {
            System.out.print("Enter array size (1-100): ");
            if (scanner.hasNextInt()) {
                size = scanner.nextInt();
                if (size >= 1 && size <= 100) {
                    originalArray = generateRandomArray(size); // Generate a random integer array of the specified size.

                    System.out.print("Original Array: ");
                    displayArray(originalArray); // Display the contents of the original array.

                    insertionSort(originalArray); // Perform insertion sort on the array.

                    System.out.print("Sorted Array: ");
                    displayArray(originalArray); // Display the sorted array.

                    break; // Exit the loop if a valid size is entered and sorting is completed.
                } else {
                    System.out.println("Invalid array size. Please enter a number between 1 and 100.");
                }
            } else {
                System.out.println("ERROR: Invalid input. Please enter a valid number between 1 and 100!");
                scanner.next(); // Consume the invalid input.
            }
        }
        scanner.close(); // Close the Scanner object to release resources.
    }
    public static int[] generateRandomArray(int size) {
        int[] array = new int[size]; // Create an integer array of the specified size.
        Random random = new Random(); // Create a Random object for generating random numbers.

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(201) - 100; // Fill the array with random integers in the range [-100, 100].
        }
        return array; // Return the generated array.
    }
    public static void displayArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + ", "); // Print each element of the array separated by commas.
        }
        System.out.println(); // Move to the next line after displaying the array.
    }
    public static void insertionSort(int[] arr) {
        int n = arr.length; // Get the length of the input array.

        for (int i = 1; i < n; i++) { // Start iterating through the array from the second element (index 1).
            int key = arr[i]; // Store the current element at index 'i' in 'key'.
            int j = i - 1; // Initialize 'j' as the index of the element just before 'i'.
            // Move elements of arr[0..i-1] that are greater than 'key' to one position ahead of their current position.
            while (j >= 0 && arr[j] > key) { // Iterate while 'j' is within bounds and the element at 'j' is greater than 'key'.
                arr[j + 1] = arr[j]; // Move the element at 'j' one position ahead.
                j--; // Decrement 'j' to compare with the previous element.
            }
            // Insert the 'key' into its correct position in the sorted portion of the array.
            arr[j + 1] = key;
            // Display the array at the end of this iteration.
            System.out.print("Iteration # " + i + ": ");
            displayArray(arr); // This line calls the 'displayArray' method to print the array.
            // Check if the array is already sorted in ascending order.
            if (isSorted(arr)) { // This line calls the 'isSorted' method to check if the array is sorted.
                break; // If the array is sorted, exit the loop early.
            }
        }
    }
    public static boolean isSorted(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[i - 1]) {
                return false; // If any element is smaller than the previous one, the array is not sorted.
            }
        }
        return true; // The array is sorted in ascending order.
    }
}
