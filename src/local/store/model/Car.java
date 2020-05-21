package local.store.model;

import local.store.ProductType;

public class Car extends Product {

    public Car(String name, double price) {
        super(ProductType.CARS, name, price);
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
