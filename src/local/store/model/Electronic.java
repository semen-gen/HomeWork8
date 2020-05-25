package local.store.model;

import local.store.ProductType;

public class Electronic extends Product {

    public Electronic(String name, double price) {
        super(ProductType.ELECTRONICS, name, price);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

}
