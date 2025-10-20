package EXPENSE_TRACKER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ExpenseImplement implements ExpenseInterface{

    private Connection conn; // field to store the DB connection
    private int userId;


    // // ✅ Constructor that accepts a Connection
    public ExpenseImplement(Connection conn, int userId){
    this.conn=conn;
    this.userId=userId;
    }

    @Override
    public void addExpense(Scanner scan) {
        System.out.println("Description: ");
        String desc = scan.nextLine();
        System.out.println("Category: ");
        String cat = scan.nextLine();
        System.out.println("Budget Amount: ");
        double ba = scan.nextDouble();
        System.out.println("Actual Amount: ");
        double aa = scan.nextDouble();
        double da = ba - aa ; // auto compute difference
        scan.nextLine();

        try{

            String sql = "INSERT INTO expenses(description,category,budgetedAmount," +
                    "actualAmount,differenceAmount,date,user_id) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,desc);//note: di kasama ang ID
            ps.setString(2,cat);
            ps.setDouble(3,ba);
            ps.setDouble(4,aa);
            ps.setDouble(5,da);
            //coconvert LocalDate -> java.sql.Date
            java.sql.Date sqlDate = java.sql.Date.valueOf(java.time.LocalDate.now());
            ps.setDate(6,sqlDate);
            ps.setInt(7,userId);//add to make sure only this user/s record is updated

            ps.executeUpdate();
            System.out.println("Expense added successfully");


        }catch (Exception e){
           e.printStackTrace();
        }

    }

    @Override
    public void viewAllExpense() { // kasama na ang ID
        System.out.println("******** Expense Tracker ******** ");
        System.out.printf("%-5s %-15s %-15s %-15s %-15s %-20s %-15s%n",
                "ID", "Description", "Category", "Budget", "Actual", "Difference", "Date");
        try {
            String sql = "SELECT * FROM expenses where user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,userId);// ito ang logged-in user's ID
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
             int id =    rs.getInt("id");
             String desc=    rs.getString("description");
             String cat=    rs.getString("category");
             double ba=    rs.getDouble("budgetedAmount");
             double ac=    rs.getDouble("actualAmount");
             double da=    rs.getDouble("differenceAmount");
             java.sql.Date date =    rs.getDate("date");


                System.out.printf("%-3d | %-15s | %-13s | %-13.2f | %-15.2f | %-13.2f | %-10s%n",
                        id, desc, cat, ba, ac, da, date.toString());

                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateExpense(Scanner scan) {
        try{
            viewAllExpense();
            System.out.println("Enter Expense ID to UPDATE: ");
            int id = scan.nextInt();
            scan.nextLine();
            //checking if the id is valid
            String checkSql = "SELECT * FROM expenses WHERE id=? AND user_id=?";
            PreparedStatement ps = conn.prepareStatement(checkSql);
            ps.setInt(1,id);
            ps.setInt(2,userId);//to ensure the current user's record
            ResultSet rs = ps.executeQuery();

            if(!rs.next()){ //kung walang mahanap stop at wag ng mag patuloy
                System.out.println("Invalid Expense ID.");
                return;// wag ng ituloy ang update
            }

            // ask for updated values
            System.out.println("Expense ID found. Proceed to UPDATE.");
            System.out.println("New Description: ");
            String desc = scan.nextLine();
            System.out.println("New Category: ");
            String cat = scan.nextLine();
            System.out.println("New Budget amount: ");
            double ba = scan.nextDouble();
            System.out.println("New Actual amount: ");
            double ac = scan.nextDouble();
            double aa = ba - ac;

            //update only the current user's record
            String sql = "UPDATE expenses SET description=?, category=?, budgetedAmount=?, actualAmount=?, " +
                    "differenceAmount=?, date=? WHERE id=? AND user_id = ?";// AND kasama ang user dito

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,desc);
            pst.setString(2,cat);
            pst.setDouble(3,ba);
            pst.setDouble(4,ac);
            pst.setDouble(5,aa);
            java.sql.Date slqDate = java.sql.Date.valueOf(java.time.LocalDate.now());
            pst.setDate(6,slqDate);
            pst.setInt(7,id);
            pst.setInt(8,userId);//add this to make sure only this user's record is updated

            int row = pst.executeUpdate();
            if(row > 0){
                System.out.println("Expense updated successfully! ");
            }else {
                System.out.println("Error.. Expense not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteExpense(Scanner scan) {

        try {

            System.out.println("Enter ID to Delete: ");
            int id = scan.nextInt();

            //check if expense exists and belongs to current user
            String sqlCheck = "SELECT * FROM expenses WHERE id=? AND user_id=?";
            PreparedStatement ps = conn.prepareStatement(sqlCheck);
            ps.setInt(1,id);
            ps.setInt(2,userId);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){// if walang mahanap na id
                System.out.println("Invalid ID");
                return;// wag ng ituloy
            }
            // to delete
            String sql = "DELETE FROM expenses WHERE id=? AND user_id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            pst.setInt(2,userId);
            int row = pst.executeUpdate();
            if(row > 0){
                System.out.println("successfully deleted");
            }else System.out.println("Error... Expense ID not found!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void viewTotalExpenses() {
        try{
            String sql = "SELECT SUM(actualAmount) AS total," +
                    "SUM(budgetedAmount) AS budget," +
                    "SUM(differenceAmount) AS  diff FROM expenses WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,userId);// need e-filter base sa logged-in user
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                double total = rs.getDouble("total");
                double budget = rs.getDouble("budget");
                double diff = rs.getDouble("diff");

                System.out.println("========================================");
                System.out.println("   TOTAL EXPENSE                        ");
                System.out.println("========================================");
                System.out.println("Total Budget: " + budget);
                System.out.println("Total Actual: " + total);
                System.out.println("Total difference: " + diff);
                System.out.println("========================================");
            }else System.out.println("No expenses found.");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
