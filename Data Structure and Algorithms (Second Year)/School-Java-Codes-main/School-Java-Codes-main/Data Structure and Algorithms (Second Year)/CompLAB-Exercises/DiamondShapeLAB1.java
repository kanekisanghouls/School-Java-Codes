import java.util.Scanner;
public class DiamondShape {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the character to draw the diamond: ");
        char character = scanner.next().charAt(0);
        System.out.print("Enter the number of characters from the center: ");
        int numChars = scanner.nextInt(); drawDiamond(character, numChars);
    }
    public static void drawDiamond(char character, int numChars) {
        for (int i = 0; i <= numChars; i++) {
            for (int j = numChars - i; j > 0; j--) {
                System.out.print(" ");
            }
            for (int k = 1; k <= i * 2 + 1; k++) {
                System.out.print(character);
            }
            System.out.println();
        }
        for (int i = numChars - 1; i >= 0; i--) {
            for (int j = numChars - i; j > 0; j--) {
                System.out.print(" ");
            }
            for (int k = 1; k <= i * 2 + 1; k++) {
                System.out.print(character);
            }
            System.out.println();
        }
    }
}
