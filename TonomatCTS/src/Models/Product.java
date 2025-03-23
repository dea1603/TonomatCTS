package Models;

public class Product {
    private String id;
    private String name;
    private double price;
    private ProductType type;

    public Product(String id, String name, double price, ProductType type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public ProductType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Produs:" + id + ',' + name + ',' + price + " ron," + type;
    }
}
