import java.io.*; 
import java.util.Scanner;
 


class Pokemon
{
   public static void main(String args[])
   {
      int yes_no, yes_no2, yes_no3;
      int gender;
      int pokemon;
      boolean options=true;
      boolean choices=true;
      boolean choices2=true;
      String name= null;
      String poke= null;
      Scanner scan = new Scanner(System.in);
      System.out.println("Welcome Trainer \n");             
      do{     
         System.out.println("What is your Name? :");
         name = scan.next();
         if(name!=null && name!=" ")
         {
            System.out.println("Is this your Name?: "+ name);
            System.out.println("1) Yes \n2) No");
            yes_no = scan.nextInt();
         
         
            if(yes_no==1)
            {
               System.out.println("Your name is "+name);
               options = true;
            }
            else if(yes_no==2)
            {
               System.out.println("This is not your name "+name);
               options = false;
            }
            else
            {
               System.out.println("Invalid Input");
                options = false;
            }
         }
         else if(name==" " && name==null)
         {
            System.out.println("Name is Empty");
         }         
                 
         
      }while(options==false);  
      
      
      System.out.println("By the way "+ name +" What are you?");
      do{
         System.out.println("Are you a boy or a girl?");
         System.out.println("1) Boy \n2) Girl"); 
         gender = scan.nextInt();
         if(gender==1)
         {
            System.out.println("Are your sure you're a Boy");
            System.out.println("1) Yes \n2) No");
            yes_no2 = scan.nextInt();
            if(yes_no2==1)
            {
               System.out.println("Yes you are a Boy");
               choices = true;
            }
            else if(yes_no2==2)
            {
               choices = false;
            }
            else{
               System.out.println("Invalid Input");
               choices = false;
            }
         
         }
         else if(gender==2)
         {
            System.out.println("Are you sure you're a Girl");
            System.out.println("1) Yes \n2) No");
            yes_no2 = scan.nextInt();
            if(yes_no2==1)
            {
               System.out.println("Yes you are a Girl");
               choices = true;
            }
            else if(yes_no2==2)
            {
               choices = false;
            }
            else{
               System.out.println("Invalid Input");
               choices= false;
            }
         
         }
         else
         {
            System.out.println("Invalid Input");
            choices= false;

         }
            
      }while(choices==false);
      
      System.out.println("Ok "+name+", Now Select your Pokemon");
      do{
         System.out.println("Select \n1) Bulbasaur \n2) Charmander \n3) Squirtle");
         pokemon = scan.nextInt();
         if(pokemon == 1)
         {
            System.out.println("You want to Choose Bulbasaur?");
            System.out.println("1) Yes \n2) No");
            yes_no3 = scan.nextInt();
            if(yes_no3==1)
            {
               System.out.println(name+" , You've Chosen Bulbasaur");
               choices2= true;
               poke ="Bulbasaur" ;
            }
            else if(yes_no3==2){
               choices2= false;
            }
            else{
               System.out.println("Invalid Input");
               choices2= false;
            }
         
                  
         }
         else if(pokemon == 2)
         {
            System.out.println("You want to Choose Charmander?");
            System.out.println("1) Yes \n2) No");
            yes_no3 = scan.nextInt();
            if(yes_no3==1)
            {
               System.out.println(name+" , You've Chosen Charmander");
               choices2= true;
               poke ="Charmander" ;
            
            }
            else if(yes_no3==2){
               choices2= false;
            }
            else{
               System.out.println("Invalid Input");
               choices2= false;
            }
         
            
         }
         else if(pokemon == 3)
         {
            System.out.println("You want to Choose Squirtle?");
            System.out.println("1) Yes \n2) No");
            yes_no3 = scan.nextInt();
            if(yes_no3==1)
            {
               System.out.println(name+" , You've Chosen Squirtle");
               choices2= true;
               poke ="Squirtle" ;
            }
            else if(yes_no3==2){
               choices2= false;
            }
            else{
               System.out.println("Invalid Input");
               choices2= false;
            }
             
         }
         else{
               System.out.println("Invalid Input");
               choices2= false;
            }

      
      }while(choices2==false);
      
      System.out.println(name+" and "+ poke +" will start your Pokemon Journey");
      System.out.println("In this Pokemon World Make new Friends");
   }

}