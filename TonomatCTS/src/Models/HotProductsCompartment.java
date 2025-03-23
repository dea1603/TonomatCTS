package Models;

import Enums.ProductType;

import java.util.List;

public class HotProductsCompartment extends Compartment {

    public HotProductsCompartment( int maxCapacity) {
        super(maxCapacity);
    }

    @Override
    public boolean addProduct(Product product) {
        if ((product.getType() == ProductType.WARM || product.getType() == ProductType.IDK)
                && products.size() < maxCapacity) {
            products.add(product);
            return true;
        }
        return false;
    }

}
