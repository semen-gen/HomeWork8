package local.store;

import java.util.*;

public abstract class Product implements Purchasable {

    private static int count;

    protected final int ID;
    protected String name;
    protected double price;
    protected ProductType type;
    protected Calendar dateRelease;


    public Product(ProductType type, String name, double price, Calendar date) {
        count += 1;
        ID = getCount();
        this.type = type;
        this.name = name;
        this.price = price;
        dateRelease = date;
    }

    public Product(ProductType type, String name, double price) {
        this(type, name, price, Calendar.getInstance());
    }

    public static int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return ID + ". " + name + " цена: " + price + " руб.";
    }

}
