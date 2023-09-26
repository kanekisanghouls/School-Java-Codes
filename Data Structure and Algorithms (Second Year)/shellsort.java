import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public class tempshell {
    public static void main(String[] args) {
        Scanner shell = new Scanner(System.in);
        int arraySize = getValidInput("Enter array size (1-100): ", shell);
        int[] array = generateRandomArray(arraySize);
        System.out.println("Array content: " + Arrays.toString(array));
        shellSort(array);
        System.out.println("Sorted Array: " + Arrays.toString(array));
        shell.close();
    }

    public static void shellSort(int[] arr) {
        int size = arr.length;
        int gap = size / 2;
        while (gap > 0) {
            for (int i = gap; i < size; i++) {
                int currentElement = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > currentElement; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = currentElement;
            }
            System.out.print("Gap " + gap + " Iteration: ");
            System.out.println(Arrays.toString(arr));
            gap /= 2; // Reduce the gap
        }
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
