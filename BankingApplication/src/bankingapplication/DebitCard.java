package bankingapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.DecimalFormat;


public class DebitCard {
    
   
    
    static void createDebitCard(Connection c, Account account) throws SQLException {
        Scanner scan = new Scanner(System.in);
        String insert = "INSERT INTO debitcard(balance, debitcardnumber, cvv, expiration, dailylimit, checkingid) " + "VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = c.prepareStatement(insert);
        pstmt.setDouble(1, 0.00);
        pstmt.setLong(2, (long)(Math.random() * (1000000000)));
        pstmt.setInt(3, (int)((Math.random() * (999 - 100) + 100 )));
        pstmt.setDate(4, new java.sql.Date(new java.util.Date().getTime() + (999999999 * 26)));
        Checking.checkCards(c, account);
        System.out.println("enter the checkingAccountID: ");
        String checkingid = scan.nextLine();
        pstmt.setDouble(Math.random());
        pstmt.setInt(6, Integer.parseInt(checkingid));
        pstmt.executeUpdate();
    }
    
    static void checkCards(Connection c, Account account) throws SQLException {
        Scanner scan = new Scanner(System.in);
        String select = "SELECT * FROM debitcard WHERE accountid = ?";
        PreparedStatement pstmt = c.prepareStatement(select);
        pstmt.setInt(1, account.getAccountID());
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(DebitCard.toString(rs.getInt(1), rs.getDouble(2), rs.getLong(3), rs.getInt(4), String.valueOf(rs.getDate(5)), rs.getInt(6)));
        }
    }
    
    static String toString(int cardid, double balance, long debitcardnumber, int cvv, String expiration, int accountid) {
        return ("cardID: " + cardid + "\nBalance: " + balance +  "\nDebit Card Number: " + debitcardnumber + "\nCVV: " + cvv + "\nExpiration: " + expiration + "\nAccountID: " + accountid + "\n");
    }
    
    static void checkBalance(Connection c, Account account) throws SQLException {
         Scanner scan = new Scanner(System.in);
         String select = "SELECT balance FROM accountid WHERE accountid = ?";
         PreparedStatement pstmt = c.prepareStatement(select);
        System.out.println("Enter accountID that you wish to check");
         String accountid = scan.nextLine();
        pstmt.setInt(1, account.getAccountID());
         ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println("Your balance on this account is: " + rs.getDouble(1));
        }
    }

   
   
}