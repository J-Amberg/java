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
            while (!"1".equals(response) && !"2".equals(response)) {
                System.out.println("Please enter a valid response");
                System.out.println("Would you like to login or create an account? Type '1' for login and '2' for create an account");
                response = scan.nextLine();
            }
            Account userAccount = new Account();
            if ("1".equals(response)) {
                userAccount = Account.login(connection);
                System.out.println(userAccount.toString());
            } else {
                Account.createAccount(connection);
                System.out.println("Now please login");
                userAccount = Account.login(connection);
                System.out.println(userAccount.toString());
            }
            System.out.println("Would you like to interact with savings, credit card, or checking/debit card?\n"
                    + "Type '1' for savings, '2' for credit card, or '3' for checking/debit card");
            response = scan.nextLine();
            while (!"1".equals(response) && !"2".equals(response) && !"3".equals(response)) {
                System.out.println("Please enter a valid response");
                System.out.println("Would you like to interact with savings, credit card, or checking/debit card?");
                System.out.println(" Type '1' for savings, '2' for credit card, or '3' for checking/debit card");
                response = scan.nextLine();
            }
            while ("1".equals(response) || "2".equals(response) || "3".equals(response) || "x".equals(response)) {

                if ("1".equals(response)) {
                    System.out.println("'1' Create a Savings\n'2' View Savings\n'3'Delete Savings");
                } else if ("2".equals(response)) {
                    while ("2".equals(response)) {

                        System.out.println("'1' to create a card\n'2' to see your cards\n'3' check balance on a card \n'4' delete a card?\n"
                                + "Type anything else when done");
                        String ccResponse = scan.nextLine();
                        if ("1".equals(ccResponse)) {
                            CreditCard.createCreditCard(connection, userAccount);
                        } else if ("2".equals(ccResponse)) {
                            CreditCard.checkCards(connection, userAccount);
                        } else if ("3".equals(ccResponse)) {
                            CreditCard.checkBalance(connection, userAccount);
                        } else if ("4".equals(ccResponse)) {
                            CreditCard.deleteCard(connection, userAccount);
                        } else {
                            response = "x";
                        }

                    }
                } else if ("3".equals(response)) {

                    while ("3".equals(response)) {
                        System.out.println("'1' for Checking\n'2' for Debit");
                        String response2 = scan.nextLine();
                        while ("1".equals(response2)) {
                            System.out.println("'1' for create a checking account\n'2' to see your checking accounts\n'3' to withdraw \n"
                                    + "'4' to deposit\n'5' to check balance\n'6' to delete a checking\nanything else when done");
                            String checkingResponse = scan.nextLine();
                            if ("1".equals(checkingResponse)) {
                                Checking.openCheckingAccount(connection, userAccount);
                            } else if ("2".equals(checkingResponse)) {
                                Checking.checkCards(connection, userAccount);
                            } else if ("3".equals(checkingResponse)) {
                                Checking.withdraw(connection, userAccount);
                            } else if ("4".equals(checkingResponse)) {
                                Checking.deposit(connection, userAccount);
                            } else if ("5".equals(checkingResponse)) {
                                Checking.checkBalance(connection, userAccount);
                            } else if ("6".equals(checkingResponse)) {
                                Checking.deleteChecking(connection, userAccount);
                            } else {
                                response = "x";
                                response2 = "x";
                            }
                        }
                        while("2".equals(response2)){
                            System.out.println("'1' to create a debit card\n'2' to check cards\n'3' to check balance of a card");
                            String debitResponse = scan.nextLine();
                            if ("1".equals(debitResponse)) {
                                DebitCard.createDebitCard(connection, userAccount);
                            } else if ("2".equals(debitResponse)) {
                                 DebitCard.checkCards(connection, userAccount);
                            } else if ("3".equals(debitResponse)) {
                                Checking.checkBalance(connection, userAccount);
                            }else {
                                response = "x";
                                response2 = "x";
                            }
                        }
                    }
                } else {
                    System.out.println("Would you like to interact with savings, credit card, or checking/debit card?");
                    System.out.println(" Type '1' for savings, '2' for credit card, or '3' for checking/debit card, anything else to quit");
                    response = scan.nextLine();
                }

            }
        } catch (SQLException e) {
            System.out.println("Error occured");
            System.out.println(e.getErrorCode());
        }

    }

}
