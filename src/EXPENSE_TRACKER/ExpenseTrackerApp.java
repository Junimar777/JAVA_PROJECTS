package EXPENSE_TRACKER;

import java.sql.Connection;
import java.util.Scanner;

public class ExpenseTrackerApp {
    private  static Connection conn = DBConnection.getConnection();
    private static Scanner scan = new Scanner(System.in);
    private  static int loggedUserID = -1; //para malaman kung sinong user ang naka log-in
    //-1 ibig sabihin wala pang user na nakalogin

    public static void main(String[] args) {
        int choice;

        // ===================LOGIN/REGISTER SYSTEM=======================
        while (loggedUserID == -1){//-1 = espesyal na value na ginagamit mo bilang "walang user" o "not logged in".
            System.out.println("\n=== LOGIN / REGISTER ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("Choose: ");
            choice = scan.nextInt();
            scan.nextLine();

            if(choice == 1)
                loggedUserID = UserAuth.login(scan);
            else if (choice == 2)
                loggedUserID = UserAuth.register(scan);
            else
                System.out.println("Invalid choice!");
        }
        ExpenseInterface expense = new ExpenseImplement(conn,loggedUserID);

        while(true){
            menu();
            choice = scan.nextInt();
            scan.nextLine();

            switch (choice){
                case 1 -> expense.addExpense(scan);
                case 2 -> expense.viewAllExpense();
                case 3 -> expense.updateExpense(scan);
                case 4 -> expense.deleteExpense(scan);
                case 5 -> expense.viewTotalExpenses();
                case 6 -> {
                    System.out.println("Logged out!");
                    loggedUserID = -1;//Kapag nag-logout si user,
                   // ibig sabihin gusto mong i-clear yung “current user”.
                    main(null); // babalik sa login/register
                    return; // stop the current session
                }
                default -> System.out.println("Invalid input. Please try again!");
            }
        }
    }


    // menu
    public static void menu(){
        System.out.println("\n============ Welcome to Expense Menu ==============");
        System.out.println("1. Add Expense");
        System.out.println("2. View All Expenses");
        System.out.println("3. Update Expense");
        System.out.println("4. Delete Expense");
        System.out.println("5. View total expenses");
        System.out.println("6. Logout");
        System.out.println("Enter choice: ");

    }


}
