package EXPENSE_TRACKER;

import java.util.Scanner;

public interface ExpenseInterface {

    void addExpense(Scanner scan);
    void viewAllExpense();
    void updateExpense(Scanner scan);
    void deleteExpense(Scanner scan);
    void viewTotalExpenses();

}
