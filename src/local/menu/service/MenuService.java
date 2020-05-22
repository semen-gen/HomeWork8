package local.menu.service;

import local.cutomers.model.Customers;
import local.cutomers.service.CustomerService;
import local.store.ProductType;
import local.store.model.Product;
import local.store.service.ProductService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class MenuService {

    private Scanner scanner;
    private CustomerService cs;
    private ProductService ps;
    private Customers currentUser;

    private final String productFile;
    private final String customerFile;

    {
        customerFile = "src\\local\\data\\customers.txt";
        productFile = "src\\local\\data\\productsFood.txt";

    }

    public MenuService() {
        scanner = new Scanner(System.in);
        cs = new CustomerService(customerFile);
        ps = new ProductService(ProductType.FOOD, productFile);
    }

    public void mainMenu() {
        printMainMenuItems();
        cycle:
        {
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    switch (scanner.nextInt()) {
                        case 1:
                            currentUser = cs.login(scanner);
                            storeMenu();
                            break;
                        case 2:
                            break cycle;
                        default:
                            System.out.println("Выбрано неверное действие");
                            printMainMenuItems();
                            break;
                    }
                }
                else {
                    System.out.println("Недопустимые символы: " + scanner.next());
                    mainMenu();
                }
            }
        }
        exit();
    }

    private void storeMenu() {
        printStoreMenuItems();
        store:
        {
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    switch (scanner.nextInt()) {
                        case 1:
                            printProducts();
                            break;
                        case 2:
                            // тут вызов метода покупки
                            break;
                        case 3:
                            break store;
                        default:
                            System.out.println("Выбрано неверное действие");
                            printMainMenuItems();
                            break;
                    }
                }
                else {
                    System.out.println("Недопустимые символы: " + scanner.next());
                    storeMenu();
                }
            }
        }
        exit();
    }

    private void printMainMenuItems() {
        System.out.println("Главное меню");
        System.out.println("============");
        System.out.println("1. Авторизоваться");
        System.out.println("2. Выйти");
    }

    private void printStoreMenuItems() {
        System.out.println("Меню магазина");
        System.out.println("=============");
        System.out.println("1. Список товара");
        System.out.println("2. Совершить покупки");
        System.out.println("3. Выйти");
    }

    private void printProducts() {
        for (HashMap.Entry<Integer, Product> item : ps.getProducts().entrySet()) {
            System.out.println(item.getValue());
        }
    }

    private void exit() {
        scanner.close();
        System.exit(0);
    }

}
