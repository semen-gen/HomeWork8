package local.store.model;

import local.store.ProductType;

public class Food extends Product {

    public Food(String name, double price) {
        super(ProductType.FOOD, name, price);
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
