import java.util.Scanner;

public class  UserNameInput 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Hello, let us know little about yourself! \n");
        System.out.println("Please enter your name: ");
        String userName = sc.nextLine(); 
        
        System.out.println("Please enter your age: ");
        String userAge = sc.nextLine();

        System.out.println("Please enter your location: ");
        String userLocation = sc.nextLine();
        
        System.out.println("Please enter your hobbies: ");
        String userHobbies = sc.nextLine();
        
        System.out.print("===================================\n");
        System.out.println("Your name: "+userName);
        System.out.println("Your age: "+userAge);
        System.out.println("Your location: "+userLocation);
        System.out.println("Your hobbies: "+userHobbies);
        System.out.print("===================================\n");
        
        System.out.print("\nThanks for sharing your little information, have a good day!");

        sc.close();
    }
}