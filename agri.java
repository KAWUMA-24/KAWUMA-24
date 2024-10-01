import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.web.bind.annotation.*;
class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalCost(int quantity) {
        return price * quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

class Order {
    private List<Product> products = new ArrayList<>();
    private double totalCost;
    private String destination;

    public void addProduct(Product product, int quantity) {
        products.add(new Product(product.getName(), product.getPrice(), quantity));
        totalCost += product.getTotalCost(quantity);
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void generateReceipt() {
        System.out.println("Receipt:");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println("Destination: " + destination);
        System.out.println("Total Cost: " + totalCost);
    }

    public void setStatus(String status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setStatus'");
    }

    public Long getId() {
        // TO DO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }
}

public class agri {
    private List<Product> products = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private double totalProfit = 0;

    public void addProduct(String name, double price, int quantity) {
        products.add(new Product(name, price, quantity));
    }

    public void displayProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void makeOrder() {
        Scanner dk = new Scanner(System.in);
        Order order = new Order();

        while (true) {
            System.out.println("Enter product name to add to order (or 'done' to finish): ");
            String name = dk.nextLine();
            if (name.equalsIgnoreCase("done")) {
                break;
            }

            Product product = findProductByName(name);
            if (product != null) {
                System.out.println("Price per unit: " + product.getPrice());
                System.out.print("Enter quantity: ");
                int quantity = dk.nextInt();
                dk.nextLine();  // Consume newline

                if (quantity <= product.getQuantity()) {
                    order.addProduct(product, quantity);
                    product.setQuantity(product.getQuantity() - quantity);
                } else {
                    System.out.println("Insufficient stock for " + product.getName());
                }
            } else {
                System.out.println("Product not found.");
            }
        }

        System.out.print("Enter destination of goods: ");
        String destination = dk.nextLine();
        order.setDestination(destination);

        orders.add(order);
        totalProfit += order.getTotalCost();
        order.generateReceipt();
    }

    private Product findProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public void generateReports() {
        System.out.println("Generating Reports...");
        System.out.println("Total Orders Made: " + orders.size());
        System.out.println("Total Profit: " + totalProfit);
        System.out.println("Products in Stock:");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println("Order Details:");
        for (Order order : orders) {
            order.generateReceipt();
            
        }
    }

    public static void main(String[] args) {
        agri business = new agri();
        Scanner mk = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to our WEB AGRI an enterprising app that afarmer can use to sell to his potential customers");
            
            System.out.println("1. Add  Stock Product");
            System.out.println("2. Display Products in Stock");
            System.out.println("3. Make Order");
            
            System.out.println();

            
            System.out.println("4. Generate Reports");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = mk.nextInt();
            mk.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = mk.nextLine();
                    System.out.print("Enter product price: ");
                    double price = mk.nextDouble();
                    System.out.print("Enter product quantity: ");
                    int quantity = mk.nextInt();
                    mk.nextLine();  // Consume newline
                    business.addProduct(name, price, quantity);
                    break;
                case 2:
                    business.displayProducts();
                    break;
                case 3:
                    business.makeOrder();
                    break;
                case 4:
                    business.generateReports();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");

            @RestController
            @RequestMapping("/orders") class OrderController{
                private Map<Long, Order>orders = new HashMap<>();

                @ PostMapping
                public Order order(@RequestBody Order order){
                    orders.put(order.getId(), order);
                    return order;
                }
                @GetMapping("/{orderId}")
                public Order getOrder(@PathVariable Long orderId){
                    return orders.get(orderId);
                }
                @PutMapping("/{orderId}")
                public Order updatOrderStatus(@PathVariable Long orderId, @RequestParam String status){
                    Order order = orders.get(orderId);
                    if(order != null){
                        order.setStatus(status);
                        orders.put(orderId, order);
                    }
                    return order;
                }
                @DeleteMapping("/{orderId}")
                public void deleteOrder(@PathVariable Long orderId){
                    orders.remove(orderId);
                }
            }

        }
    }
}
}
