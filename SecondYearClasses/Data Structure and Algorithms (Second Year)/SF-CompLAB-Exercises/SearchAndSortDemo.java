import java.util.Arrays;
import java.util.Scanner;

public class SearchAndSortDemo {

    // Linear Search for unsorted data
    public static int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i; // Return the index where the target is found
            }
        }
        return -1; // Return -1 if the target is not found
    }

    // Binary Search for sorted data
    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid; // Return the index where the target is found
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1; // Return -1 if the target is not found
    }

    // Bubble Sort
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap array[j] and array[j+1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Selection Sort
    public static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap array[i] and array[minIndex]
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    // Insertion Sort
    public static void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            // Move elements of array[0..i-1] that are greater than key to one position ahead of their current position
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();

        int[] array = new int[size];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        System.out.print("Enter the element to search: ");
        int target = scanner.nextInt();

        // Linear Search
        int linearSearchResult = linearSearch(array, target);
        if (linearSearchResult != -1) {
            System.out.println("Linear Search: Element found at index " + linearSearchResult);
        } else {
            System.out.println("Linear Search: Element not found");
        }

        // Sort the array before binary search
        Arrays.sort(array);

        // Binary Search
        int binarySearchResult = binarySearch(array, target);
        if (binarySearchResult != -1) {
            System.out.println("Binary Search: Element found at index " + binarySearchResult);
        } else {
            System.out.println("Binary Search: Element not found");
        }

        // Display the original array
        System.out.println("Original Array: " + Arrays.toString(array));

        // Bubble Sort
        int[] bubbleSortArray = Arrays.copyOf(array, array.length);
        bubbleSort(bubbleSortArray);
        System.out.println("Bubble Sort: " + Arrays.toString(bubbleSortArray));

        // Selection Sort
        int[] selectionSortArray = Arrays.copyOf(array, array.length);
        selectionSort(selectionSortArray);
        System.out.println("Selection Sort: " + Arrays.toString(selectionSortArray));

        // Insertion Sort
        int[] insertionSortArray = Arrays.copyOf(array, array.length);
        insertionSort(insertionSortArray);
        System.out.println("Insertion Sort: " + Arrays.toString(insertionSortArray));
    }
}
