package Models;

import Enums.ProductType;


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
    }

    @Override
    public boolean isCompatible(Product product) {
        return product.getType() == ProductType.COLD || product.getType() == ProductType.IDK;
    }
}
