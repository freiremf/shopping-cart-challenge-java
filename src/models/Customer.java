package models;

public class Customer {
    private final String name;
    private final String email;
    private final CreditCard creditCard;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.creditCard = new CreditCard();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    @Override
    public String toString() {
        return
        "Customer info: \n" +
        "Name: " + this.name + "\n" +
        "Email: " + this.email + "\n" +
        "Available limit: R$" + this.creditCard.getLimit();
    }
}
