package Models;

import java.util.List;

public class ColdProductsCompartment extends Compartment{

    public ColdProductsCompartment(List<Product> products, int maxCapacity) {
        super(products, maxCapacity);
    }

    @Override
    public boolean addProduct(Product product) {
        if ((product.getType() == ProductType.COLD || product.getType() == ProductType.IDK)
                && products.size() < maxCapacity) {
            products.add(product);
            return true;
        }
        return false;
    }
}
