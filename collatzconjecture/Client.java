package collatzconjecture;

import java.util.Scanner;

// NOTE: this program does not yet work on negatives, it will soon.


public class Client {
    
    public static void main(String[] args) {
        CollatzConjecture method = new CollatzConjecture();
        Scanner scan = new Scanner(System.in);
        String input;
        while(true){
            System.out.println("Please enter any integer of any length, without any leading zeroes");
            boolean valid = true;
            input = scan.next();
            if(input.charAt(0) == '0'){
                continue;
            }
            for(int i = 0; i < input.length(); i++){
                if(!isDigit(input.charAt(i))){
                    valid = false;
                }
            }
            if(valid)
                break;
        } 
        method.performCollatz(input); 
    }
    
    public static boolean isDigit(char c){ //checks if char is digit.
        if(c == '0' || c == '1' || c == '2' || c == '3' || c == '4'
        || c == '5' || c == '6' || c == '7' || c == '8' || c == '9'){
            return true;
        }
        return false;
    }
    
}
