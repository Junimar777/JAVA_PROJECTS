package EXPENSE_TRACKER;


import java.time.LocalDate;

public class Expense {
    private int id;
    private String description;
    private String category;
    private double budgetedAmount;
    private double actualAmount;
    private double differenceAmount;
    private LocalDate createdDate;//automatic current date

    //constructor
    public Expense(String description,String category,double budgetedAmount,double actualAmount,double differenceAmount){
    this.description =description;
    this.category=category;
    this.budgetedAmount=budgetedAmount;
    this.actualAmount=actualAmount;
    this.differenceAmount=differenceAmount;
    this.createdDate = LocalDate.now();//automatic date
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getBudgetedAmount() {
        return budgetedAmount;
    }

    public void setBudgetedAmount(double budgetedAmount) {
        this.budgetedAmount = budgetedAmount;
    }

    public double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public double getDifferenceAmount() {
        return differenceAmount;
    }

    public void setDifferenceAmount(double differenceAmount) {
        this.differenceAmount = differenceAmount;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
