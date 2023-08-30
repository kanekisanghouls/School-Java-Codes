import java.util.Scanner;
public class BarChart {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] puntos = new int[5];
        String[] names = {"", "", "", "", ""}; // any names of the person.
        for (int i = 0; i < names.length; i++) {
            System.out.print("Enter points scored by " + names[i] + " - ");
            puntos[i] = input.nextInt();
        }
        System.out.println("Points for Game: ");
        for (int i = 0; i < names.length; i++) {
            System.out.print(names[i] + " : ");
            for (int j = 0; j < puntos[i]; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
