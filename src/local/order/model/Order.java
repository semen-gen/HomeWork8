package local.order.model;

import local.cutomers.model.Customer;
import local.store.model.Product;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private Customer customer;
    private HashMap<Integer, Product> items;

    public Order(Customer current) {
        customer = current;
        items = new HashMap<>();
    }

    public HashMap<Integer, Product> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addItem(Product item) {
        items.put((count() + 1), item);
    }

    public int count() {
        return items.size();
    }

    public double getSum() {
        double sum = 0;
        for (Map.Entry<Integer, Product> item : items.entrySet()) {
            sum += item.getValue().getPrice();
        }
        return sum;
    }

}
