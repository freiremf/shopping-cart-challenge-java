package models;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<String, Double> cart;

    public Cart() {
        this.cart = new HashMap<>();
    }

    public Map<String, Double> add(String product, double price) {
        this.cart.put(product, price);
        return this.cart;
    }

    public Map<String, Double> remove(String product) {
        this.cart.remove(product);
        return this.cart;
    }

    public Map<String, Double> getCart() {
        return cart;
    }
}
