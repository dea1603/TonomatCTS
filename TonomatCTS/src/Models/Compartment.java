package Models;

import java.util.ArrayList;
import java.util.List;

public abstract class Compartment {
    protected List<Product> products = new ArrayList<>();
    protected int maxCapacity;

    public Compartment(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public abstract void addProduct(Product product);
    public abstract boolean isCompatible(Product product);


    public Product extractProduct(int id) {
        for (Product p : products) {
            if (p.getId()==id) {
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
