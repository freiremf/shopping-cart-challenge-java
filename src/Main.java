import models.Cart;
import models.Customer;
import models.Product;

import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        var store = createStore();
        var reader = new Scanner(System.in);

        System.out.println("""
        ************************************
        Enter your name:
        ************************************
        """);
        String name = reader.nextLine();

        System.out.println("""
        ************************************
        Enter your email address:
        ************************************
        """);
        String email = reader.nextLine();

        Customer customer = new Customer(name, email);

        System.out.println("""
        ************************************
        Enter your credit card limit:
        ************************************
        """);
        double limit = reader.nextDouble();

        customer.getCreditCard().addLimit(limit);
        Cart cart = new Cart();

        int option;

        do {
            System.out.println("""
                    ************************************
                    Choose an option:
                    1 - Choose a product
                    2 - See the cart
                    3 - Clear cart
                    4 - Checkout
                    5 - See details customer and credit card
                    6 - Show shop ordered by name
                    7 - Finish
                    """);

            option = reader.nextInt();
            reader.nextLine();

            switch (option) {
                case 1:
                    chooseProduct(store, reader, cart);
                    break;
                case 2:
                    System.out.println(cart.getCart());
                    break;
                case 3:
                    cart.getCart().clear();
                    break;
                case 4:
                    checkout(customer, cart);
                    break;
                case 5:
                    System.out.println(customer);
                    break;
                case 6:
                    System.out.println(store.stream().sorted(Comparator.comparing(Product::name)).toList());
                    break;
                case 7:
                    checkout(customer, cart);
                    System.out.println("See you later!");
                    break;
                default:
                    System.out.println("Invalid option. Try again!");
            }
        } while (option != 7);
    }

    public static void chooseProduct(List<Product> store, Scanner reader, Cart cart) {
        System.out.println("""
        ************************************
        Choose a product (type the product name):
        ************************************
        """);
        store.forEach(System.out::println);
        String productName = reader.nextLine();
        Optional<Product> product = getProduct(productName, store);

        if (product.isEmpty()) {
            System.out.println("Product not found");
            return;
        }
        cart.add(product.get().name(), product.get().price());
        System.out.println("Product added to the cart successfully!");
    }

    public static void checkout(Customer customer, Cart cart) {
        double total = cart.getCart().values().stream().mapToDouble(Double::doubleValue).sum();
        if (customer.getCreditCard().useLimit(total)) {
            System.out.println("Purchase completed successfully");
            cart.getCart().clear();
        } else {
            System.out.println("Purchase not completed");
        }
        System.out.println(customer);
    }

    public static List<Product> createStore() {
        Product shoes = new Product("Shoes pair", 80.0);
        Product tShirt = new Product("T-Shirt", 50.0);
        Product pants = new Product("Pants", 90.0);
        Product cap = new Product("Cap", 70.0);

        return Stream.of(shoes, tShirt, pants, cap)
                .sorted(Comparator.comparing(Product::price))
                .toList();
    }

    public static Optional<Product> getProduct(String productName, List<Product> store) {
        return store.stream()
                .filter(product -> product.name().equalsIgnoreCase(productName))
                .findFirst();
    }
}
