public class JavaOperators {
  public static void main(String[] args) {
    System.out.println("Java Arithmetic Operators");
    int x = 10; int y = 5;
    System.out.print("x is 10 and y is 5");
    System.out.println("x + y = " + (x + y)); System.out.println("x - y = " + (x - y)); 
    System.out.println("x * y = " + (x * y)); System.out.println("x / y = " + (x / y)); 
    System.out.println("x % y = " + (x % y)); 

    System.out.println("\nJava Relational Operators");
    System.out.println("x == y: " + (x == y)); System.out.println("x != y: " + (x != y));
    // Equal to                                // Not equal to
    System.out.println("x > y: "  + (x > y)); System.out.println("x < y: "  + (x < y));
    // Greater than                            // Less than
    System.out.println("x >= y: " + (x >= y)); System.out.println("x <= y: " + (x <= y));
    // Greater than or equal to                // Less than or equal to

    System.out.println("\nJava Logical Operators");
    boolean a = true; boolean b = false;
    System.out.println("a && b: " + (a && b)); System.out.println("a || b: " + (a || b)); 
    System.out.println("!(a && b): " + !(a && b)); 
  }
}
