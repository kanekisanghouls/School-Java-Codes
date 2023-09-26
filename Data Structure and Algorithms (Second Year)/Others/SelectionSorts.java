import java.util.Random;
import java.util.Scanner;
public class SelectionSortLAB3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size;
        do {
            System.out.print("Enter array size (1-100): ");
            size = scanner.nextInt();
            if (size < 1 || size > 100) {
                System.out.println("ERROR: Array size. Please enter a number between 1 and 100!");
            }
        } while (size < 1 || size > 100);
        int[] array = generateRandomArray(size);
        System.out.print("Original Array: ");
        displayArray(array); // Display the original array

        selectionSort(array);

        System.out.print("Sorted Array: ");
        displayArray(array); // Display the sorted array

        scanner.close();
    }
    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(201) - 100;
        }
        return array;
    }
    public static void displayArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + ", ");
        }
        System.out.println();
    }
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
            System.out.print("Main Iteration " + (i + 1) + ": ");
            displayArray(arr);
        }
    }
}
