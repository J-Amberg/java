package bankingapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.ResultSet;
/**
 * @author caden
 */
public class Account {
    private int accountID;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    
    //Use one connection, pass connection as parameter to the methods
    public Account(int id, String fname, String lname, String uname, String pwd, String phnum, String eml){
        accountID = id;
        firstname = fname;
        lastname = lname;
        username = uname;
        password = pwd;
        phoneNumber = phnum;
        email = eml;
    }
    public Account(){
        
    }
    
    public static Account login(Connection c) throws SQLException {
        //execute select from account where username = name and password = pwd;
        Scanner scan = new Scanner(System.in);
        String select = "select * from account where username = ? and password = ?";
        PreparedStatement pstmt = c.prepareStatement(select);
        System.out.println("Enter username: ");
        String uname =scan.nextLine();
        System.out.println("Enter password: ");
        String pwd = scan.nextLine();
        pstmt.setString(1, uname);
        pstmt.setString(2, pwd);
        ResultSet rs = pstmt.executeQuery();
        int id = 0;
        String fname = "", lname = "", user = "", pass = "", pnum = "", eml = "";
        while(rs.next())
        {
            id = rs.getInt(1);
            fname = rs.getString(2);
            lname = rs.getString(3);
            user = rs.getString(4);
            pass = rs.getString(5);
            pnum = rs.getString(6);
            eml = rs.getString(7);
        }
        return new Account(id, fname, lname, user, pass, pnum, eml);
    }
    
    public static void createAccount(Connection c) throws SQLException {
        Scanner scan = new Scanner(System.in);
        String insert = "INSERT INTO account(fname, lname, username, password, phonenum, email) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = c.prepareStatement(insert);
        System.out.println("Enter username");
        String username = scan.nextLine();
        System.out.println("Enter password");
        String password = scan.nextLine();
        System.out.println("Enter first name");
        String fname = scan.nextLine();
        System.out.println("Enter last name");
        String lname = scan.nextLine();
        System.out.println("Enter phone number");
        String phoneNum = scan.nextLine();
        System.out.println("Enter email");
        String email = scan.nextLine();
        pstmt.setString(1, fname);
        pstmt.setString(2, lname);
        pstmt.setString(3, username);
        pstmt.setString(4, password);
        pstmt.setString(5, phoneNum);
        pstmt.setString(6, email);
        pstmt.executeUpdate();
    }
    
    public String toString(){
        return "accountID: " + accountID + ";\n first name: " + firstname +
                ";\n last name: " + lastname + ";\n username: " + username + ";\n password: " + password
                + ";\n phone number: " + phoneNumber + ";\n email: " + email;
    }
    
    public void changePassword(Connection c) throws SQLException{
        Scanner scan = new Scanner(System.in);
        String upd = "Update account set password = ? where accountID = " + this.accountID;
        PreparedStatement pstmt = c.prepareStatement(upd);
        System.out.println("Enter new Password: ");
        String newPwd = scan.nextLine();
        pstmt.setString(1, newPwd);
        this.password = newPwd;
        pstmt.executeUpdate();
        
    }
    
    public void changeEmail(Connection c) throws SQLException{
        Scanner scan = new Scanner(System.in);
        String upd = "Update account set email = ? where accountID = " + this.accountID;
        PreparedStatement pstmt = c.prepareStatement(upd);
        System.out.println("Enter new Email: ");
        String newEml = scan.nextLine();
        pstmt.setString(1, newEml);
        this.email = newEml;
        pstmt.executeUpdate();
    }
    
    public void changeUsername(Connection c) throws SQLException{
        Scanner scan = new Scanner(System.in);
        String upd = "Update account set username = ? where accountID = " + this.accountID;
        PreparedStatement pstmt = c.prepareStatement(upd);
        System.out.println("Enter new Username: ");
        String newU = scan.nextLine();
        pstmt.setString(1, newU);
        this.username = newU;
        pstmt.executeUpdate();    
    }
    
    public void changePhoneNumber(Connection c) throws SQLException{
        Scanner scan = new Scanner(System.in);
        String upd = "Update account set phoneNumber = ? where accountID = " + this.accountID;
        PreparedStatement pstmt = c.prepareStatement(upd);
        System.out.println("Enter new Phone Number: ");
        String newPnum = scan.nextLine();
        pstmt.setString(1, newPnum);
        this.phoneNumber = newPnum;
        pstmt.executeUpdate();
    }
    
    public int getAccountID(){return accountID;}
    
    public String getFirst(){return firstname;}
    
    public String getLast(){ return lastname;}
    
    public String getUsername(){return username;}
    
    public String getPhoneNumber(){return phoneNumber;}
    
    public String getEmail(){return email;}
}