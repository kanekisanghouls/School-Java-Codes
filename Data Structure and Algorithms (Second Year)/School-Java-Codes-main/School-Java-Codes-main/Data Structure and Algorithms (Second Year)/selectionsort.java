import java.util.Random;
import java.util.Scanner;

public class SelectionSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter array size (1-100): ");
        int arraySize;
        while (true) {
            try {
                arraySize = Integer.parseInt(scanner.nextLine());
                if (arraySize >= 1 && arraySize <= 100) {
                    break;
                } else {
                    System.out.print("Invalid Input Array Size! \nEnter array size(1-100): ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid Input Array Size! \nEnter array size(1-100): ");
            }
        }
        int[] array = new int[arraySize];
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(201) - 100;
        }
        System.out.print("Array content: ");
        for (int i = 0; i < arraySize; i++) {
            System.out.print(array[i]);
            if (i < arraySize - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        selectionSort(array);
        System.out.print("Sorted array: ");
        for (int i = 0; i < arraySize; i++) {
            System.out.print(array[i]);
            if (i < arraySize - 1) {
                System.out.print(", ");
            }
        }
    }

    public static void selectionSort(int[] array) {
        int size = array.length;
        for (int i = 0; i < size - 1; i++) {
            boolean swapped = true; // Track if any swaps are made in this iteration
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                    swapped = false; // Swap occurred
                }
            }
            
            // Swap elements
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;

            // Print iteration
            System.out.print("Iteration " + (i + 1) + ": ");
            for (int num : array) {
                System.out.print(num + ", ");
            }
            System.out.println();
        }
    }
}
