package Models;

import java.util.List;

public class HotProductsCompartment extends Compartment {

    public HotProductsCompartment(List<Product> products, int maxCapacity) {
        super(products, maxCapacity);
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
