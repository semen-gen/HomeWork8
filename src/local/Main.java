package local;

import local.cutomers.model.Customer;
import local.store.ProductType;
import local.store.service.ProductService;


public class Main {

    public static void main(String[] args) {
        ProductService pcFood = new ProductService(ProductType.FOOD, "src\\local\\data\\productsFood.txt");
        System.out.println(pcFood.getProducts());

        Customer user1 = new Customer("ivan", "qwerty");
        Customer user2 = new Customer("semen", "123456");
        Customer user3 = new Customer("olga", "zxcvb");


    }

}
