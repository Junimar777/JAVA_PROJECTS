package EXPENSE_TRACKER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class UserAuth {
    //create the db connection
    private static Connection conn = DBConnection.getConnection();


    //Register function
    public static int register(Scanner scan){
        try {
            System.out.println("Enter username: ");
            String username = scan.nextLine();
            System.out.println("Enter password: ");
            String password = scan.nextLine();

            //check if username already exists
            String checkSql = "SELECT * FROM users WHERE username=?";
            PreparedStatement ps = conn.prepareStatement(checkSql);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("Username already exists!");
                // kaya negative -1 ginagamit sa simple error tulad ng error/duplicate/invalid input
                return -1;
            }

            // insert new user
            String sql = "INSERT INTO users(username, password) VALUES(?,?)";
            PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1,username);
            pst.setString(2,password);
            pst.executeUpdate();
            ResultSet keys = pst.getGeneratedKeys();
            if(keys.next()){
                int newID = keys.getInt(1);//kunin ung unang column kaya 1
                System.out.println("Registered successfully! Your user ID: " + newID);
                return  newID;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return  -1;
    }

    // LOGIN FUNCTION
    public static int login(Scanner scan){
        try {
            System.out.println("Enter username: ");
            String username = scan.nextLine();
            System.out.println("Enter password: ");
            String password = scan.nextLine();

            String sql = "SELECT * FROM users WHERE username=? AND password =? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int userID = rs.getInt("id");//para malaman cnung user ang nag log.in
                System.out.println("✅ Login successful! Welcome, " + username);
                return userID;
            }else {
                System.out.println("Invalid credentials");
                return  -1; // para bumalik sa login/register menu
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  -1; //ibalik -1 kung failed login
    }


}
