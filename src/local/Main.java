package local;

import local.cutomers.Customer;
import local.store.*;

import java.util.*;


public class Main {

    public static void main(String[] args) {
        Product product1 = new Food("Помидоры", 5.3);
        Product product2 = new Food("Огурцы", 3.3);
        Product product3 = new Food("Свинина", 11.65);
        Product product4 = new Food("Курица", 4.93);
        HashMap<Integer, Product> products = new HashMap<>();
        products.put(product1.getID(), product1);
        products.put(product2.getID(), product2);
        products.put(product3.getID(), product3);
        products.put(product4.getID(), product4);
        System.out.println(products);

        Customer user1 = new Customer("ivan", "qwerty");
        Customer user2 = new Customer("semen", "123456");
        Customer user3 = new Customer("olga", "zxcvb");

    }

}
