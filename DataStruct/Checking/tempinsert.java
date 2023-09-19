import java.util.Random;
import java.util.Scanner;

public class tempinsert {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter array size (1-100): ");
        int size;
        while (true) {
            try {
                size = Integer.parseInt(scanner.nextLine());
                if (size >= 1 && size <= 100) {
                    break;
                } else {
                    System.out.print("Invalid size. Please enter a number between 1 and 100: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid size. Please enter a number between 1 and 100: ");
            }
        }
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(201) - 100;
        }
        System.out.print("Array content: ");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        insertionSort(array);
        System.out.print("Sorted array: ");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }
    }

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            swapped = false;
            
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                swapped = true;
            }

            arr[j + 1] = key;

            if (swapped) {
                System.out.print("Iteration " + i + ": ");
                for (int num : arr) {
                    System.out.print(num + ", ");
                }
                System.out.println();
            }
        }
    }
}
