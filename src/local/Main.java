package local;

import local.cutomers.service.CustomerService;
import local.store.ProductType;
import local.store.service.ProductService;


public class Main {

    public static void main(String[] args) {
        ProductService pcFood = new ProductService(ProductType.FOOD, "src\\local\\data\\productsFood.txt");
        System.out.println(pcFood.getProducts());

        CustomerService cs = new CustomerService("src\\local\\data\\customers.txt");
        System.out.println(cs.getCustomers());

    }

}
