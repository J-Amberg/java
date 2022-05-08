package bankingapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author jordan.amberg
 */
public class BankingApplication {
    
        public static void main(String[] args) throws SQLException {
        String jdbcURL = "jdbc:postgresql://localhost:5432/BankingApplication";
        String username = "postgres";
        String password = "password123";
        try { 
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            Statement stmt = connection.createStatement();
            Scanner scan = new Scanner(System.in);
            System.out.println("Would you like to login or create an account? Type '1' for login and '2' for create an account");
            String response = scan.nextLine();
            while(!"1".equals(response) && !"2".equals(response)){
                System.out.println("Please enter a valid response");
                System.out.println("Would you like to login or create an account? Type '1' for login and '2' for create an account");
                response = scan.nextLine();
            }
            Account userAccount = new Account();
            if("1".equals(response)){
                userAccount = Account.login(connection);
                System.out.println(userAccount.toString());
            }else{
                Account.createAccount(connection);
                System.out.println("Now please login");
                userAccount = Account.login(connection);
                System.out.println(userAccount.toString());
            }
            System.out.println("Would you like to interact with savings, credit card, or checking/debit card?\n"
                    + "Type '1' for savings, '2' for credit card, or '3' for checking/debit card");
            response = scan.nextLine();
            while(!"1".equals(response) && !"2".equals(response) && !"3".equals(response)){
                System.out.println("Please enter a valid response");
                System.out.println("Would you like to interact with savings, credit card, or checking/debit card?");
                System.out.println(" Type '1' for savings, '2' for credit card, or '3' for checking/debit card");
                response = scan.nextLine();
            }
            if("1".equals(response)){
                //savings interaction here
            }else if("2".equals(response)){
                while("2".equals(response)){
                    
                    System.out.println("Would you like to create a card, see your cards, or check balance on a card?\n"
                            + "Type '1' to create a card, '2' to see your cards, or '3' to check balance on a card. Type anything else when done");
                    String ccResponse = scan.nextLine();
                    if("1".equals(ccResponse)){
                        CreditCard.createCreditCard(connection, userAccount);
                    }else if("2".equals(ccResponse)){
                        CreditCard.checkCards(connection, userAccount);
                    }else if("3".equals(ccResponse)){
                        CreditCard.checkBalance(connection, userAccount);
                    }else{
                        response = "x";
                    }
                    
                }
            }else{
                //checking/debit card interaction here
            }
            
        }
        catch (SQLException e) { 
            System.out.println("Error occured");
            System.out.println(e.getErrorCode());
        }
        
        

    }
    
}
