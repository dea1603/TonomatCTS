package Models;

import Enums.ProductType;

import java.util.List;

public class ColdProductsCompartment extends Compartment{

    public ColdProductsCompartment(int maxCapacity) {
        super( maxCapacity);
    }

    @Override
    public void addProduct(Product product) {
        if ((product.getType() == ProductType.COLD || product.getType() == ProductType.IDK)
                && products.size() < maxCapacity) {
            products.add(product);
        }
        else {
            System.out.println("Produsul nu se poate adauga!");
        }    }
}
