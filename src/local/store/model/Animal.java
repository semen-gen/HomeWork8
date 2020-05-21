package local.store.model;

import local.store.ProductType;

public class Animal extends Product {

    public Animal(String name, double price) {
        super(ProductType.ANIMALS, name, price);
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
