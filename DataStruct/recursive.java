public class Factorial {
    public static void main(String[] args) {
        int n = 5; // Replace with the desired number for which you want to calculate the factorial
        long result = calculateFactorial(n);
        System.out.println("Factorial of " + n + " is " + result);
    }

    public static long calculateFactorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * calculateFactorial(n - 1);
        }
    }
}
