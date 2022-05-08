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
 *
 * @author jordan.amberg
 */
public class CreditCard {
    public CreditCard(){}
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    static void createCreditCard(Connection c, Account account) throws SQLException{
        Scanner scan = new Scanner(System.in);
        String insert = "INSERT INTO creditcard(balance, creditlimit, interestrate, creditcardnumber, cvv, expiration, accountid) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = c.prepareStatement(insert);
        pstmt.setDouble(1, 0.00);
        pstmt.setDouble(2, (double)(int)((Math.random() *(3000 - 500)) + 500));
        String interestrate = df.format((double)((Math.random() * (0.4 - 0.15) + 0.15)));
        pstmt.setDouble(3, Double.parseDouble(interestrate));
        pstmt.setLong(4, (long)(Math.random() * (1000000000)));
        pstmt.setInt(5, (int)((Math.random() * (999 - 100) + 100 )));
        pstmt.setDate(6,  new java.sql.Date(new java.util.Date().getTime() + (999999999 * 26)));
        pstmt.setInt(7, account.getAccountID());
        pstmt.executeUpdate();
    }
    
    static void deleteCard(Connection c, Account account) throws SQLException{
        Scanner scan = new Scanner(System.in);
        String delete = "DELETE FROM creditcard WHERE creditcardid = ? AND accountid = ?";
        PreparedStatement pstmt = c.prepareStatement(delete);
        System.out.println("Please enter the card id for the card you'd like to delete:");
        String cardid = scan.nextLine();
        pstmt.setInt(1, Integer.parseInt(cardid));
        pstmt.setInt(2, account.getAccountID());
        pstmt.executeUpdate();
        System.out.println("Deleted card: " + cardid);
    }
    
    static void checkCards(Connection c, Account account) throws SQLException{
        Scanner scan = new Scanner(System.in);
        String select = "SELECT * FROM creditcard WHERE accountid = ?";
        PreparedStatement pstmt = c.prepareStatement(select);
        pstmt.setInt(1, account.getAccountID());
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            System.out.println(CreditCard.toString(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4), rs.getLong(5), rs.getInt(6), 
                    String.valueOf(rs.getDate(7)), rs.getInt(8)));
        }
    }
    
    static String toString(int cardid, double balance, double creditlimit, double interestrate, long creditcardnumber, int cvv, String expiration, int accountid){
        return ("cardID: " + cardid + "\nBalance: " + balance + "\nCredit Limit:" + creditlimit + "\nInterest Rate: " + interestrate
                + "\nCredit Card Number: " + creditcardnumber + "\nCVV: " + cvv + "\nExpiration: " + expiration
                + "\nAccountID: " + accountid + "\n");
    }
    
    static void checkBalance(Connection c, Account account) throws SQLException{
        Scanner scan = new Scanner(System.in);
        String select = "SELECT balance FROM creditcard WHERE accountid = ? AND creditcardid = ?";
        PreparedStatement pstmt = c.prepareStatement(select);
        System.out.println("Enter cardID that you wish to check");
        String creditcardid = scan.nextLine();
        pstmt.setInt(1, account.getAccountID());
        pstmt.setInt(2, Integer.parseInt(creditcardid));
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            System.out.println("Your balance on this card is: " + rs.getDouble(1));
        }
        
    }
}
