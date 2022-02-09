package numberconverter;

/**
 * Simple converter that can convert back and forth between binary and decimal, octal and decimal, and hex and decimal 
 * @author jordan.amberg
 * @version 2.9.2022
 */
public class NumberConverter {

    public static void main(String[] args) {
        System.out.println(binaryToDecimal("100011"));
        System.out.println(decimalToBinary(35));
        System.out.println(octalToDecimal("34537123"));
        System.out.println(decimalToOctal(7519827));
        System.out.println(hexToDecimal("234ef12"));
        System.out.println(decimalToHex(37023506));
    }
    
    public static int binaryToDecimal(String binary){
        String tempString = "";
        int decimal = 0;
        for(int i = binary.length() - 1; i >= 0; i--){
            tempString += binary.charAt(i);
        }
        for(int i = 0; i < tempString.length(); i++){
            if(tempString.charAt(i) == '1')
                decimal += Math.pow(2, i);
        }
        return decimal;
    }
    
    public static String decimalToBinary(int decimal){
        String reversedBinary = "", binary = "";
        while(decimal > 0){
            if(decimal % 2 == 1){
                reversedBinary += "1";
            }else
                reversedBinary += "0";
            decimal /= 2;
        }
        for(int i = reversedBinary.length() - 1; i >= 0; i--){
            binary += String.valueOf(reversedBinary.charAt(i));
        }
        return binary;
    }
    
    public static int octalToDecimal(String octal){
        String tempString = "";
        int decimal = 0;
        for(int i = octal.length() - 1; i >= 0; i--){
            tempString += octal.charAt(i);
        }
        for(int i = 0; i < tempString.length(); i++){
            decimal += (Math.pow(8, i) * Integer.valueOf(String.valueOf(tempString.charAt(i))));
        }
        return decimal;
    }
    
    public static String decimalToOctal(int decimal){
        String reversedOctal = "", octal = "";
        while(decimal > 0){
            int currentValue = decimal % 8;
            reversedOctal += String.valueOf(currentValue);
            decimal /= 8;
        }
        for(int i = reversedOctal.length() - 1; i >= 0; i--){
            octal += String.valueOf(reversedOctal.charAt(i));
        }
        return octal;
    }
    
    public static int hexToDecimal(String hex){
        String tempString = "";
        int decimal = 0;
        for(int i = hex.length() - 1; i >= 0; i--){
            tempString += hex.charAt(i);
        }
        int currentValue;
        for(int i = 0; i < tempString.length(); i++){
            switch(tempString.charAt(i)){
                case 'a': 
                    currentValue = 10;
                    break;
                case 'b': 
                    currentValue = 11;
                    break;
                case 'c': 
                    currentValue = 12;
                    break;
                case 'd': 
                    currentValue = 13;
                    break;
                case 'e': 
                    currentValue = 14;
                    break;
                case 'f': 
                    currentValue = 15;
                    break;
                default:
                    currentValue = Integer.valueOf(String.valueOf(tempString.charAt(i)));
            }
            decimal += (Math.pow(16, i) * currentValue);
        }
        return decimal;
    }
    
    public static String decimalToHex(int decimal){
        String reversedHex = "", hex = "";
        while(decimal > 0){
            int currentValue = decimal % 16;
            String currentHexValue = "";
            switch(currentValue){
                case 10: 
                    currentHexValue = "a";
                    break;
                case 11: 
                    currentHexValue = "b";
                    break;
                case 12: 
                    currentHexValue = "c";
                    break;
                case 13: 
                    currentHexValue = "d";
                    break;
                case 14: 
                    currentHexValue = "e";
                    break;
                case 15: 
                    currentHexValue = "f";
                    break;
                default:
                    currentHexValue = String.valueOf(currentValue);
                    break;
            }
            reversedHex += String.valueOf(currentHexValue);
            decimal /= 16;
        }
        for(int i = reversedHex.length() - 1; i >= 0; i--){
            hex += String.valueOf(reversedHex.charAt(i));
        }
        return hex;
    }
}
