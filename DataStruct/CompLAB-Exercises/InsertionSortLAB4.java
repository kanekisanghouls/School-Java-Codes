import java.util.Random;
import java.util.Scanner;

public class tempinsert {
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
        insertionSort(array);
        System.out.print("Sorted array: ");
        for (int i = 0; i < arraySize; i++) {
            System.out.print(array[i]);
            if (i < arraySize - 1) {
                System.out.print(", ");
            }
        }
        scanner.close();
    }

    public static void insertionSort(int[] arr) {
        int arrayLength = arr.length;
        for (int i = 1; i < arrayLength; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;

            System.out.print("Iteration " + i + ": ");
            for (int num : arr) {
                System.out.print(num + ", ");
            }
            System.out.println();
        }
    }
}
