import java.util.Random;
import java.util.Scanner;

public class BubbleSort {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter array size (1-100): ");
		int size = scanner.nextInt();

		if (size < 1 || size > 100) {
			System.out.println("Invalid array size. Please enter a number between 1 and 100.");
			return;
		}

		int[] array = new int[size];
		Random random = new Random();

		for (int i = 0; i < size; i++) {
			array[i] = random.nextInt(201) - 100; // Generate random integers between -100 and 100
		}

		System.out.print("Array content: ");
		for (int num : array) {
			System.out.print(num + ", ");
		}
		System.out.println();

		bubbleSort(array);

		scanner.close();
	}

	public static void bubbleSort(int[] arr) {
		int n = arr.length;
		boolean swapped;

		for (int i = 0; i < n - 1; i++) {
			swapped = false;
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					// Swap arr[j] and arr[j + 1]
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					swapped = true;
				}
			}
			if (!swapped) {
				break;
			}
			System.out.print("Main iteration " + (i + 1) + ": ");
			for (int num : arr) {
				System.out.print(num + ", ");
			}
			System.out.println();
		}
	}
}
