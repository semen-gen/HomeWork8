package local.menu.service;

import local.cutomers.model.Customers;
import local.cutomers.service.CustomerService;
import local.store.ProductType;
import local.store.service.ProductService;

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
        productFile = "src\\local\\data\\productFood.txt";

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
                            cs.login(scanner);
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

    private void printMainMenuItems(){
        System.out.println("Главное меню");
        System.out.println("============");
        System.out.println("1. Авторизоваться");
        System.out.println("2. Выйти");
    }

    private void exit() {
        scanner.close();
        System.exit(0);
    }

}
