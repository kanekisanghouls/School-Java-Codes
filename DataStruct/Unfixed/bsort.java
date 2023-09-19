import java.util.*;
public class bubbleSortedjavaArray {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n, i;
        System.out.print("Array Size (1-100): ");
        n = s.nextInt();
        int[] a = new Random().ints(n, -100, 101).toArray();
        for (i = 0; i < n - 1; i++) {
            boolean swapped = true;
            for (int j = 0, t; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    t = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = t;
                    swapped = true;
                }
            }
            System.out.println("Main Iteration " + (i + 1) + ": " + Arrays.toString(a));
            if (!swapped)
            break;
        }
        System.out.println("\nOriginal Arrays: " + Arrays.toString(a));
        System.out.println("\nSorted Arrays: " + Arrays.toString(a));
        s.close();
    }
}
