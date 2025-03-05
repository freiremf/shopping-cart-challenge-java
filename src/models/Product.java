package models;

public record Product(String name, double price) {

    @Override
    public String toString() {
        return this.name + " - R$" + this.price;
    }
}
