package local;

import local.menu.model.Menu;
import local.store.ProductType;

public class Main {

    public static void main(String[] args) {
//        ProductService pcFood = new ProductService(ProductType.FOOD, "src\\local\\data\\productsFood.txt");
//        System.out.println(pcFood.getProducts());
//
//        CustomerService cs = new CustomerService("src\\local\\data\\customers.txt");
//        System.out.println(cs.getCustomers());

        Menu menu = new Menu(ProductType.FOOD);
        menu.start();

    }

}
