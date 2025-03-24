package Models;

import Enums.ProductType;

import java.util.List;

public class HotProductsCompartment extends Compartment {

    public HotProductsCompartment( int maxCapacity) {
        super(maxCapacity);
    }

    @Override
    public void addProduct(Product product) {
        if ((product.getType() == ProductType.WARM || product.getType() == ProductType.IDK)
                && products.size() < maxCapacity) {
            products.add(product);
        }
        else {
            System.out.println("Produsul "+product.getName()+" nu se poate adauga!");
        }
    }
}
