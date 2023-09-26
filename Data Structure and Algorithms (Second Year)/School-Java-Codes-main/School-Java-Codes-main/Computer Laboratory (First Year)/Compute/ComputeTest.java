import java.util.Scanner;
public class ComputeTest {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int choice;
    System.out.println("Choose one below:");
    System.out.println("1. Circle \n2. Square");
    System.out.print("Enter a choice: ");
    choice = input.nextInt();
    if (choice == 1) {
      int circleOption;
      double r;
      System.out.println("\nCircle Options:");
      System.out.println("1. Circumference \n2. Area \n3. Diameter");
      System.out.print("Enter an option: ");
      circleOption = input.nextInt();
      System.out.print("Enter the radius: ");
      r = input.nextDouble();
      if (circleOption == 1) {
        double circumference = ComputeSquareCircle.computeCircumference(r);
        System.out.println("The Circumference of a Circle is " + circumference);
      } else if (circleOption == 2) {
        double area = ComputeSquareCircle.computeArea(r);
        System.out.println("The Area of a Circle is " + area);
      } else if (circleOption == 3) {
        double diameter = ComputeSquareCircle.computeDiameter(r);
        System.out.println("The Diameter of a Circle is " + diameter);
      } else {
        System.out.println("Invalid option");
      }
    } else if (choice == 2) {
      int squareOption;
      double a;
      System.out.println("\nSquare Options:");
      System.out.println("1. Area \n2. Perimeter");
      System.out.print("Enter an option: ");
      squareOption = input.nextInt();
      System.out.print("Enter the side length: ");
      a = input.nextDouble();
      if (squareOption == 1) {
        double area = ComputeSquareCircle.computeSquareArea(a);
        System.out.println("The Area of a Square is " + area);
      } else if (squareOption == 2) {
        double perimeter = ComputeSquareCircle.computeSquarePerimeter(a);
        System.out.println("The Perimeter of a Square is " + perimeter);
      } else {
        System.out.println("Invalid option! Sayup balik uno.");
      }
    } else {
      System.out.println("Invalid choice! Sayup balik uno.");
    }
  }
}
