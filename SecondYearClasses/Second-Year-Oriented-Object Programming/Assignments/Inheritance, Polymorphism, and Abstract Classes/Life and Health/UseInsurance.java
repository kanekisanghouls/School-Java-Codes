import java.util.Scanner;
public class UseInsurance {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;
        Insurance insurance;
        while (true) {
            System.out.println("Select an insurance type:");
            System.out.println("1. Life Insurance");
            System.out.println("2. Health Insurance");
            try {
                choice = input.nextInt();
                if (choice == 1) {
                    insurance = new Life();
                    break;  
                } else if (choice == 2) {
                    insurance = new Health();
                    break;  
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter 1 or 2.");
                input.next(); 
            }
        }
        insurance.display();
        input.close();
    }
}
