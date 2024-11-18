public class Product {
    private String productId;  // Change from int to String
    private String name;
    private String category;
    private double price;

    // Constructor
    public Product(String productId, String name, String category, double price) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    // Getters
    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product [ID=" + productId + ", Name=" + name + ", Category=" + category + ", Price=" + price + "]";
    }
}
