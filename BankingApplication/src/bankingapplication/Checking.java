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

/**
 * @author jordan.amberg
 */

public class Checking {
    
    static void openCheckingAccount(Connection c, Account account)throws SQLException{
        Scanner scan = new Scanner(System.in);
        String insert = "INSERT INTO checking (balance, checkingaccountnumber, accountid) "
                + "VALUES (?, ?, ?) ";
        PreparedStatement pstmt = c.prepareStatement(insert);
        pstmt.setDouble(1, 0.00);
        pstmt.setLong(2, (long)(Math.random() * (1000000000)));
        pstmt.setInt(3, account.getAccountID());
        pstmt.executeUpdate();
        System.out.println("Checking created");
    }
    
    static void withdraw(Connection c, Account account)throws SQLException{
        Scanner scan = new Scanner(System.in);
        String update = "UPDATE checking SET balance = ? WHERE accountid = ? AND checkingid = ?";
        PreparedStatement pstmt = c.prepareStatement(update);
        System.out.println("Insert the checkingid to withdraw from");
        String checkingid = scan.nextLine();
        System.out.println("How much would you like to withdraw? xxx.xx format");
        String depositAmount = scan.nextLine();
        String getBalance = "SELECT balance FROM checking WHERE accountid = ? AND checkingid = ?";
        PreparedStatement pstmt1 = c.prepareStatement(getBalance);
        pstmt1.setInt(1, account.getAccountID());
        pstmt1.setInt(2, Integer.parseInt(checkingid));
        ResultSet rs = pstmt1.executeQuery();
        double newBalance = 0.00;
        while(rs.next()){
            newBalance = rs.getDouble(1) - Double.parseDouble(depositAmount);
        }
        pstmt.setDouble(1, newBalance);
        pstmt.setInt(2, account.getAccountID());
        pstmt.setInt(3, Integer.parseInt(checkingid));
        pstmt.executeUpdate();
        System.out.println("Your new balance is $" + newBalance);
    }
    
    static void deposit(Connection c, Account account)throws SQLException{
        Scanner scan = new Scanner(System.in);
        String update = "UPDATE checking SET balance = ? WHERE accountid = ? AND checkingid = ?";
        PreparedStatement pstmt = c.prepareStatement(update);
        System.out.println("Insert the checking accountid for which you'd like to make a deposit");
        String checkingid = scan.nextLine();
        System.out.println("How much would you like to deposit? xxx.xx format");
        String depositAmount = scan.nextLine();
        String getBalance = "SELECT balance FROM checking WHERE accountid = ? AND checkingid = ?";
        PreparedStatement pstmt1 = c.prepareStatement(getBalance);
        pstmt1.setInt(1, account.getAccountID());
        pstmt1.setInt(2, Integer.parseInt(checkingid));
        ResultSet rs = pstmt1.executeQuery();
        double newBalance = 0.00;
        while(rs.next()){
            newBalance = rs.getDouble(1);
        }
        newBalance += Double.parseDouble(depositAmount);
        System.out.println(newBalance);
        pstmt.setDouble(1, newBalance);
        pstmt.setInt(2, account.getAccountID());
        pstmt.setInt(3, Integer.parseInt(checkingid));
        pstmt.executeUpdate();
        System.out.println("Your new balance is $" + newBalance);
    }
    
    static void checkCards(Connection c, Account account) throws SQLException{
        Scanner scan = new Scanner(System.in);
        String select = "SELECT * FROM checking WHERE accountid = ?";
        PreparedStatement pstmt = c.prepareStatement(select);
        pstmt.setInt(1, account.getAccountID());
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            System.out.println(Checking.toString(rs.getInt(1), rs.getDouble(2), rs.getLong(3), rs.getInt(4)));
        }
    }
    
    static String toString(int cardid, double balance, long checkingaccountnumber, int accountid){
        return ("checkingID: " + cardid + "\nBalance: " + balance + "\nChecking Account Number" + checkingaccountnumber + "\nAccountID: " + accountid +"\n");
    }
    
    static void checkBalance(Connection c, Account account)throws SQLException{
        Scanner scan = new Scanner(System.in);
        String select = "SELECT balance FROM checking WHERE accountid = ? AND checkingid = ?";
        PreparedStatement pstmt = c.prepareStatement(select);
        System.out.println("Enter checkingID that you wish to check");
        String debitcardid = scan.nextLine();
        pstmt.setInt(1, account.getAccountID());
        pstmt.setInt(2, Integer.parseInt(debitcardid));
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            System.out.println("Your balance on this account is: " + rs.getDouble(1));
        }
    }
}
