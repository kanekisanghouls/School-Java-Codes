import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
public class InsertionSortLAB4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size;
        do {
            System.out.print("Enter array size (1-100): ");
            size = scanner.nextInt();
            if (size < 1 || size > 100) {
                System.out.println("ERROR: Array size. Please enter a number between 1 and 100!");
            }
            System.out.println();
        } while (size < 1 || size > 100);
        int[] array = generateRandomArray(size);
        System.out.println("Original Array: ");
        displayArray(array);
        System.out.println();

        insertionSort(array);

        System.out.println("Sorted Array (Insertion Sort): ");
        displayArray(array);
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
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
}
