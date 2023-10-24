import java.io.*;
import java.util.*;
public class NullPointerException {
    public static void main(String[] args) {
    Scanner scanner = new Scanner (System.in);
       System.out.println("Enter word: 'Error' to null the String"); 
       System.out.print("Enter string: ");
        String str = scanner.nextLine();
        
        try {
            
           
            if (str.equalsIgnoreCase("Error")){
              str = null; 
               int length = str.length();
            } else if (!str.equalsIgnoreCase("Error")){
              System.out.println("String is valid!");
            }
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e.getMessage());
        }
    }
}
