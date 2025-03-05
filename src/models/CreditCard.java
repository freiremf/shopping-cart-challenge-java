package models;

public class CreditCard {
    private double limit;

    public void addLimit (double limit) {
        this.limit += limit;
    }

    public boolean useLimit (double amount) {
        if(amount > limit) {
            System.out.println("Limit exceeded");
            return false;
        }
        this.limit -= amount;
        return true;
    }

    public double getLimit() {
        return limit;
    }
}
