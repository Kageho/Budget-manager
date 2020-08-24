package budget;

import java.util.ArrayList;

final class Budget {
    private double expenses;
    private double balance;
    private final ArrayList<Purchase> purchases;

    public Budget() {
        this.purchases = new ArrayList<>();
        this.expenses = 0;
        this.balance = 0;
    }

    public void addIncome(double income) {
        this.balance += income;
    }

    public void updateBalanceFromFile(double value) {
        this.balance += value;
    }

    public void addPurchase(Purchase purchase) {
        this.purchases.add(purchase);
        this.expenses += purchase.getPrice();
        this.balance -= purchase.getPrice();
    }

    public void addPurchaseFromFile(Purchase purchase) {
        this.purchases.add(purchase);
        this.expenses += purchase.getPrice();
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public double getBalance() {
        return this.balance;
    }

    public double getExpenses() {
        return expenses;
    }
}
