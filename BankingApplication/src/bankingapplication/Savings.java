/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.util.Random;
import java.sql.Statement;
/**
 *
 * @author caden
 */
public class Savings {
    
    public static void viewAcc(Connection c, int accID) throws SQLException{
        Scanner scan = new Scanner(System.in);
        String select = "SELECT * FROM savings WHERE accountid = ?";
        PreparedStatement pstmt = c.prepareStatement(select);
        pstmt.setInt(1, accID);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            System.out.println("Saving account ID: " + rs.getInt(1));
            System.out.println("Balance: " + rs.getFloat(1));
            System.out.println("Interest Rate: " + rs.getFloat(1));
            System.out.println("Minimum Balance: " + rs.getFloat(1));
            System.out.println("Saving account Number: " + rs.getLong(5));
            System.out.println("Account ID: " + rs.getInt(6));
        }
    }
    
    public static void addSavings(Connection c, int accID) throws SQLException
    {
        Random rand = new Random();
        float intRate = rand.nextInt(76) * 0.001f;
        Scanner scan = new Scanner(System.in);
        String add = "Insert into savings(balance, interestRate, "
                + "minimumBalance, savingAccountNumber, accountID) values(?, ?, 100, " +accID +");";
        PreparedStatement pstmt = c.prepareStatement(add);
        System.out.println("How much will you deposit for starting balance? (Minimum is 100)");
        int minBal = Integer.parseInt(scan.nextLine());
        pstmt.setInt(1, minBal);
        pstmt.setFloat(2, intRate);
        pstmt.executeUpdate();
    }
    
    public static void deleteSavings(Connection c, int accID) throws SQLException
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Which account do wish to delete?");
        int savAccID = Integer.parseInt(scan.nextLine());
        System.out.println("Which account should the money be transferred to before deletion?");
        String ot = "select savingID from savings where accountID = " + accID + " and savingID != " + savAccID;
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(ot);
        String ot1 = "select checkingID from checking where accountID = " + accID;
        Statement stmt1 = c.createStatement();
        ResultSet rs1 = stmt1.executeQuery(ot1);
        while(rs.next())
        {
            System.out.println("Savings Account " + rs.getInt(1));
        }
        while(rs1.next())
        {
            System.out.println("Checking Account " + rs1.getInt(1));
        }
        
        System.out.println("Enter the corresponding type of account for transfer:");
        String type = scan.nextLine();
        System.out.println("Enter the number that corresponds with proper account for transfer: ");
        int account = Integer.parseInt(scan.nextLine());
        
        String selAmount = "select balance from savings where savingID = " + savAccID;
        Statement stmt2 = c.createStatement();
        ResultSet rs2 = stmt2.executeQuery(selAmount);
        float amount = 0;
        while(rs2.next())
        {
            amount = rs2.getFloat(1);
            System.out.println(amount);
        }
        
        String upd = "update " + type + " set balance = balance + " + amount
                + "where accountID = " + accID + " and " + type +"ID = "+ account;
        PreparedStatement pstmt = c.prepareStatement(upd);
        pstmt.executeUpdate();
        
        String del = "delete from savings where accountID = " + accID + " and savingID = " + savAccID;
        PreparedStatement pstmt1 = c.prepareStatement(del);
        pstmt1.executeUpdate();
    }
    
    public static void checkBalance(Connection c) throws SQLException
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Which account do wish to delete?");
        int savAccID = Integer.parseInt(scan.nextLine());
        String select = "select balance from savings where savingID = " + savAccID;
        PreparedStatement pstmt = c.prepareStatement(select);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next())
        {
            System.out.println("Balance: " + rs.getFloat(1));
        }
    }
    
    public static void deposit(Connection c) throws SQLException
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Which account do wish to deposit to?");
        int savAccID = Integer.parseInt(scan.nextLine());
        String upd = "update savings set balance = balance + ? where savingID = " + savAccID;
        System.out.println("Enter amount to deposit: ");
        float amount = Float.parseFloat(scan.nextLine());
        PreparedStatement pstmt = c.prepareStatement(upd);
        pstmt.setFloat(1, amount);
        pstmt.executeUpdate();
    }
    
    public static void withdraw(Connection c) throws SQLException
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Which account do wish to withdraw from?");
        int savAccID = Integer.parseInt(scan.nextLine());
        String upd = "update savings set balance = balance - ? where savingID = " + savAccID;
        System.out.println("Enter amount to withdraw: ");
        float amount = Float.parseFloat(scan.nextLine());
        PreparedStatement pstmt = c.prepareStatement(upd);
        pstmt.setFloat(1, amount);
        pstmt.executeUpdate();
    }
    
    public static void changeIntRate(Connection c) throws SQLException
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Which account do wish to change the interest rate for?");
        int savAccID = Integer.parseInt(scan.nextLine());
        String upd = "update savings set interestRate = ? where savingID = " + savAccID;
        Random rand = new Random();
        float intRate = rand.nextInt(76) * 0.001f;
        PreparedStatement pstmt = c.prepareStatement(upd);
        pstmt.setFloat(1, intRate);
        System.out.println("New interest rate is " + intRate);
    }
    
}
