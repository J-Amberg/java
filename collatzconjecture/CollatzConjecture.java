package collatzconjecture;

/**
 * @author jordan.amberg
 * @version 11.9.2021
 * The Collatz Conjecture (named after Lothar Collatz) is a simple math pattern 
 * where you start with any number n. If n is even divide by two, if n is odd perform
 * 3n + 1. Any starting number will theoretically(unproven) get stuck in an infinite loop between
 * 4 -> 2 -> 1 -> 4 ... etc. 
 */
public class CollatzConjecture {
    
    int iterations = 0;
    String peak = "";
    
    public CollatzConjecture(){}
    
    public void performCollatz(String n){ //revursive formula for 3n + 1 problem.
        iterations++;
        if(compareStrings(n, peak) == 1){
            peak = n;
        }
        int endsWith = Integer.parseInt(String.valueOf(n.charAt(n.length() - 1)));
        System.out.println(n);
        String reiterate;
        if(endsWith % 2 == 0){ //if even
            reiterate = DivideNByTwo(n);
        }
        else{ //odd
            reiterate = ThreeNPlusOne(n);
        }
        if(Integer.parseInt(String.valueOf(n.charAt(n.length() - 1))) != 1 || n.length() != 1){
            performCollatz(reiterate);
        }
        else{
            System.out.println("The peak was: " + peak);
            System.out.println("The number of iterations was: " + iterations);
        }
    }
    //124
    public static String DivideNByTwo(String input){//Long division function that works on any string (to avoid long bit limit)
        int remainder = 0;
        String output = "";
        for(int i = 0; i < input.length(); i++){
            int current = Integer.parseInt(String.valueOf(input.charAt(i))); 
            if(remainder == 10){ 
                current += remainder; //adds remainder to the current character
                remainder = 0;
            }
            if(current % 2 != 0){
                remainder = 10; //remainder can only be 1 or 0 since dividing by two
            }
            if(i == 0 && current / 2 == 0)
                continue;
            output += current / 2;
        }
        return output; 
    }//103
    public static String ThreeNPlusOne(String input){ //this function will multiply any string by 3 and add 1
        int carry = 0;
        String output = "";
        for(int i = input.length() - 1; i >= 0; i--){
            int temp = Integer.parseInt(String.valueOf(input.charAt(i))); //takes current char
            temp *= 3; 
            int temp2 = temp + carry; //3
            if(temp2 / 10 == 0){ 
                output += temp2; //"903"
                carry = 0; 
                continue;
            }
            carry = temp2 / 10; 
            output += temp2 % 10; 
        }
        if(carry != 0) //check if there is still a carry
            output += carry; //399
        String reverseOutput = ""; //'output' is currently backwards, temp will reverse it
        carry = Integer.parseInt(String.valueOf(output.charAt(0))) + 1;
        if(carry == 10){ //checks if carry is needed at the end
            int numNines = 0;
            for(int i = output.length() - 1; i >= 0; i--){
                reverseOutput += output.charAt(i);
                if(output.charAt(i) == '9'){
                    numNines++; //gets the number of nines in the sequence
                }
            }
            if(output.length() == numNines){
                output = "1"; //this will execute if the number is all nines (999 -> 1000)
                for(int i = 0; i < numNines; i++){
                    output += 0;
                }
                return output;
            }
            int leadingNumber = Integer.parseInt(String.valueOf(reverseOutput.charAt(reverseOutput.length() - 1 - numNines))) + 1;
            System.out.println(leadingNumber);
            output = ""; 
            for(int i = 0; i < reverseOutput.length() - 1 - numNines; i++){ 
                output += reverseOutput.charAt(i); //will get the regular number(s) before the "leading" number (ex: the 3 in 309)
            }
            output += leadingNumber;
            for(int i = 0; i < numNines; i++){
                output += 0; //add zeroes
            }
            return output;
        }
        for(int i = output.length() - 1; i > 0; i--){
            reverseOutput += output.charAt(i); //reverses output
        }
        reverseOutput += carry;
        output = reverseOutput;
        return output;
    }
    
    public static int compareStrings(String x1, String x2){ //returns 1 if string one is bigger than string 2, otherwise 0
        if(x1.length() > x2.length())
            return 1;
        if(x1.length() < x2.length())
            return 0;
        for(int i = 0; i < x1.length(); i++){
            if(x1.charAt(i) > x2.charAt(i))
                return 1;
            if(x1.charAt(i) < x2.charAt(i))
                return 0;
        }
        return 0;
    }
}
