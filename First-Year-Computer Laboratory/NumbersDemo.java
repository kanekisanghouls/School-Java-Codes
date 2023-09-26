import java.util.Scanner;
public class NumbersDemo{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer >> ");
        int num1 = scanner.nextInt();
        System.out.print("Enter another integer >> ");
        int num2 = scanner.nextInt();
        displayTwiceTheNumber(num1); displayTwiceTheNumber(num2);
        displayNumberPlusFive(num1); displayNumberPlusFive(num2);
        displayNumberSquared(num1); displayNumberSquared(num2);
    }
    public static int displayTwiceTheNumber(int num) {
        int result = num * 2;
        System.out.println(num + " times 2 is " + result);
        return result;
    }
    public static int displayNumberPlusFive(int num) {
        int result = num + 5;
        System.out.println(num + " plus 5 is " + result);
        return result;
    }
    public static int displayNumberSquared(int num) {
        int result = num * num;
        System.out.println(num + " squared is " + result);
        return result;
    }
}
