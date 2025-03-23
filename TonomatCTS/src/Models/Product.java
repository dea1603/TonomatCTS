package Models;

import Enums.ProductType;

public class Product {
    private static int counter=0;
    private int id;
    private String name;
    private double price;
    private ProductType type;

    public Product(String name, double price, ProductType type) {
        this.id = ++counter;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
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
        return "ID: " + id + ", Name: " + name + ", Price: " + price+ ", Type: " + type;
    }
}
