package local;

import local.store.*;


public class Main {

    public static void main(String[] args) {
        Product product1 = new Food("Помидоры", 5.3);
        Product product2 = new Food("Огурцы", 3.3);
        Product product3 = new Food("Свинина", 11.65);
        Product product4 = new Food("Курица", 4.93);

        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);
        System.out.println(product4);
    }

}
