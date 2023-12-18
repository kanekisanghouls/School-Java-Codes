// LIBRARY MANAGEMENT (SIMPLE JAVA GUI CODE)

import java.io.*;
public class LMS {
    public static void lms(String UserName, String Password){
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter("user_credentials.txt",true));
            output.write(UserName+" ");
            output.write(Password+" ");
            output.newLine();
            output.close();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }
    
    public static void displayAll(){
        try {
            String strCurrentLine, data[]=new String[1000];
            FileReader file = new FileReader("user_credentials.txt");
            BufferedReader input = new BufferedReader(file);
            System.out.println();
            System.out.println("Data in the Data Bank:");
            while ((strCurrentLine = input.readLine()) != null) {
                //System.out.println(strCurrentLine);
            }
            input.close();
        }

        catch(Exception e) {
            e.getStackTrace();
        }    
    }
}
