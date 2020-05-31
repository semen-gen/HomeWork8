package local.order.model;

import local.cutomers.model.Customer;
import local.store.model.Product;

import java.util.HashMap;
import java.util.Map;

public class Order {

  private final Customer CUSTOMER;
  private HashMap<Integer, Product> items;

  public Order(Customer current) {
    CUSTOMER = current;
    items = new HashMap<>();
  }

  public HashMap<Integer, Product> getOrderItems() {
    return items;
  }

  public Customer getOrderCustomer() {
    return CUSTOMER;
  }

  public void addItem(Product item) {
    items.put((count() + 1), item);
  }

  public int count() {
    return items.size();
  }

  public double getOrderSum() {
    double sum = 0;
    for (Map.Entry<Integer, Product> item : items.entrySet()) {
      sum += item.getValue().getPrice();
    }
    return sum;
  }

  public String listOrderItems() {
    StringBuilder list = new StringBuilder();
    for (Map.Entry<Integer, Product> item : items.entrySet()) {
      list.append(item.getKey().toString())
          .append(". ")
          .append(item.getValue().getName())
          .append('\n');
    }
    return list.toString();
  }

  public void clearOrder() {
    items = new HashMap<>();
  }

}
