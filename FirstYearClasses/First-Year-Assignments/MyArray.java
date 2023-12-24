import java.util.*;
public class MyArray {
    public static void main(String[] args) {
        int[] MyArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        System.out.println("Integers from first to last:");
        for (int i = 0; i < MyArray.length; i++) {
            System.out.println(MyArray[i]);
        }
        System.out.println("Integers from last to first:");
        for (int i = MyArray.length - 1; i >= 0; i--) {
            System.out.println(MyArray[i]);
        }
    }
}
