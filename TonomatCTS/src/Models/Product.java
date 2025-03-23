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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public ProductType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Produs:" + id + ',' + name + ',' + price + " ron," + type;
    }
}
