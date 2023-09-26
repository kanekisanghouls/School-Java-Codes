import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class tempshells {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = getValidInput("Enter array size (1-100): ", scanner);

        int[] array = generateRandomArray(size);
        System.out.println("Array content: " + Arrays.toString(array));

        shellSort(array);

        System.out.println("Sorted Array: " + Arrays.toString(array));
        scanner.close();
    }

    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
            System.out.print("Gap " + gap + " Iteration: ");
            System.out.println(Arrays.toString(arr));
        }
    }

    private static int getValidInput(String message, Scanner sc) {
        int size;
        while (true) {
            try {
                System.out.print(message);
                size = sc.nextInt();
                if (size >= 1 && size <= 100) {
                    return size;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 100.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer. Try again.");
                sc.nextLine();
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
