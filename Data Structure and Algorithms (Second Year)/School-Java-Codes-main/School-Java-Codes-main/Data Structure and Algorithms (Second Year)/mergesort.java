import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MergeSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arraySize = getValidInput("Enter array size (1-100): ", scanner);

        int[] array = generateRandomArray(arraySize);
        System.out.println("Array content: " + Arrays.toString(array));

        mergeSort(array, 0, array.length - 1);

        System.out.println("Sorted Array: " + Arrays.toString(array));
        scanner.close();
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;

            // Sort the left and right halves recursively
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);

            // Merge the sorted halves
            merge(arr, left, middle, right);
        }
    }

    public static void merge(int[] arr, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; i++) {
            leftArray[i] = arr[left + i];
        }
        for (int i = 0; i < n2; i++) {
            rightArray[i] = arr[middle + 1 + i];
        }

        // Print left and right arrays
        System.out.println("Left Array: " + Arrays.toString(leftArray));
        System.out.println("Right Array: " + Arrays.toString(rightArray));

        // Merge the two arrays
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray[] and rightArray[]
        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }

        // Print merged array
        System.out.println("Merged Array: " + Arrays.toString(arr));
    }

    private static int getValidInput(String message, Scanner sc) {
        int size;
        while (true) {
            try {
                System.out.print(message);
                String input = sc.nextLine();
                size = Integer.parseInt(input);
                if (size >= 1 && size <= 100) {
                    return size;
                } else {
                    System.out.println("Invalid Input Array Size!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input Array Size!");
            }
        }
    }

    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(201) - 100;
        }
        return array;
    }
}
