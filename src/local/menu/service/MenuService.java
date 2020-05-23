package local.menu.service;

import local.cutomers.model.Customer;
import local.cutomers.service.CustomerService;
import local.store.ProductType;
import local.store.model.Product;
import local.store.service.ProductService;

import java.util.HashMap;
import java.util.Scanner;

public class MenuService {

    private final Scanner SCANNER;
    private final CustomerService CS;
    private final ProductService PS;
    private Customer currentUser;

    private final String productFile;
    private final String customerFile;

    {
        customerFile = "src\\local\\data\\customers.txt";
        productFile = "src\\local\\data\\productsFood.txt";

    }

    public MenuService() {
        SCANNER = new Scanner(System.in);
        CS = new CustomerService(customerFile);
        PS = new ProductService(ProductType.FOOD, productFile);
    }

    public void mainMenu() {
        printMainMenuItems();
        cycle:
        {
            while (SCANNER.hasNext()) {
                if (SCANNER.hasNextInt()) {
                    switch (SCANNER.nextInt()) {
                        case 1:
                            currentUser = CS.login(SCANNER);
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
                    System.out.println("Недопустимые символы: " + SCANNER.next());
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
            while (SCANNER.hasNext()) {
                if (SCANNER.hasNextInt()) {
                    switch (SCANNER.nextInt()) {
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
                    System.out.println("Недопустимые символы: " + SCANNER.next());
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
        for (HashMap.Entry<Integer, Product> item : PS.getProducts().entrySet()) {
            System.out.println(item.getValue());
        }
    }

    private void exit() {
        SCANNER.close();
        System.exit(0);
    }

}
