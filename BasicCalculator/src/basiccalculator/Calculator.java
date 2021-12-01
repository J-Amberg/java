package basiccalculator;

/**
 *
 * @author jordan.amberg
 * @version 12.1.2021
 */
public class Calculator {
    
    public Calculator(){
        
    }
    
    public static double divide(double dividend, double divisor){
        return dividend / divisor;
    }
    
    public static double multiply(double firstNumber, double secondNumber){
        return firstNumber * secondNumber;
    }
    
    public static double add(double firstNumber, double secondNumber){
        return firstNumber + secondNumber;
    }
    
    public static double subtract(double minuend, double subtrahend){
        return minuend - subtrahend;
    }
    
    public static double applyExponent(double constant, double exponent){
        return Math.pow(constant, exponent);
    }
    
    public static double performOperation(char operator, double num1, double num2){
        switch(operator){
            case '-':
                return subtract(num1, num2);
            case '/':
                return divide(num1, num2);
            case '+':
                return add(num1, num2);
            case '*':
                return multiply(num1, num2);
            case '^':
                return applyExponent(num1, num2);
            case '2':
                return applyExponent(num1, 2);
            case 's':
                return applyExponent(num1, 0.5);
        }
        return 0.0;
    }
    
}
