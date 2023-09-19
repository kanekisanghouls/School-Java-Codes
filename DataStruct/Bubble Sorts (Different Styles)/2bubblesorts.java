import java.util.*;

public class BubbleSort {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Array Size (1-100): ");
        int n = s.nextInt();
        int[] arraySize = new Random().ints(n, -100, 101).toArray();
        System.out.println("\nOriginal Array: " + Arrays.toString(arraySize));
        bubbleSort(arraySize);
        System.out.println("\nSorted Array: " + Arrays.toString(arraySize));
        s.close();
    }
    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            System.out.println("Main Iteration " + (i + 1) + ": " + Arrays.toString(array));

            if (!swapped) {
                // If no swaps were made in this iteration, the array is already sorted.
                break;
            }
        }
    }
}
