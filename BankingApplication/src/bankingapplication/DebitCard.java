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
        String insert = "INSERT INTO debitcard(debitcardnumber, cvv, expiration, dailylimit, checkingid) " + "VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstmt = c.prepareStatement(insert);
        pstmt.setLong(1, (long)(Math.random() * (1000000000)));
        pstmt.setInt(2, (int)((Math.random() * (999 - 100) + 100 )));
        pstmt.setDate(3, new java.sql.Date(new java.util.Date().getTime() + (999999999 * 26)));
        Checking.checkCards(c, account);
        System.out.println("enter the checkingAccountID: ");
        String checkingid = scan.nextLine();
        pstmt.setDouble(4, 500.00);
        pstmt.setInt(5, Integer.parseInt(checkingid));
        pstmt.executeUpdate();
    }
    
    static void checkCard(Connection c, Account account) throws SQLException {
        Scanner scan = new Scanner(System.in);
        String select = "SELECT * FROM debitcard WHERE checkingid = ?";
        PreparedStatement pstmt = c.prepareStatement(select);
        Checking.checkCards(c, account);
        System.out.println("enter checkingID that you wish to check");
        int userSelect = scan.nextInt();
        pstmt.setInt(1, userSelect);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(DebitCard.toString(rs.getInt(1), rs.getLong(2), rs.getInt(3), String.valueOf(rs.getDate(4)), rs.getDouble(5), rs.getInt(6)));
        }
    }
    
    static String toString(int cardid, long debitcardnumber, int cvv, String expiration, double dailyLimit, int checkingID) {
        return ("cardID: " + cardid + "\nDebit Card Number: " + debitcardnumber + "\nCVV: " + cvv + "\nExpiration: " + expiration + 
                "\nDaily Limit" + dailyLimit + "\nCheckingID: " + checkingID + "\n");
    }
    
    static void checkBalance(Connection c, Account account)throws SQLException{
        Scanner scan = new Scanner(System.in);
        String select = "SELECT balance FROM checking WHERE checkingid = ?";
        PreparedStatement pstmt = c.prepareStatement(select);
        Checking.checkCards(c, account);
        System.out.println("Enter checkingID that you wish to check");
        String debitcardid = scan.nextLine();
        pstmt.setInt(1, Integer.parseInt(debitcardid));
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            System.out.println("Your balance on this account is: " + rs.getDouble(1));
        }
    }

   
   
}