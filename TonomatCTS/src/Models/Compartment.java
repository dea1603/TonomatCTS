package Models;

import java.util.ArrayList;
import java.util.List;

public abstract class Compartment {
    protected List<Product> products;
    protected int maxCapacity;

    public Compartment(List<Product> products, int maxCapacity) {
        this.products = products;
        this.maxCapacity = maxCapacity;
    }

    public abstract boolean addProduct(Product product);

    public Product removeProduct(String id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                products.remove(p);
                return p;
            }
        }
        return null;
    }

    public List<Product> getProducts() {
        return products;
    }

}
