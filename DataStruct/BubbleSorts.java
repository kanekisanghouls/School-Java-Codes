import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
public class BubbleSortLAB2 {
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
        System.out.print("Array content: ");
        displayArray(array);
        bubbleSort(array);
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
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
            System.out.print("Main Iteration" + (i + 1) + ": ");
            displayArray(arr);
        }
    }
}
