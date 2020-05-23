package local.menu.service;

import local.cutomers.model.Customer;
import local.cutomers.service.CustomerService;
import local.store.ProductType;
import local.store.model.Product;
import local.store.service.ProductService;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                            login();
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
                            printProducts(null);
                            break;
                        case 2:
                            purchaseMenu();
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

    private void login() {
        System.out.println("Авторизация");
        System.out.println("===========");
        System.out.println("Введите логин:");
        String login = SCANNER.next();
        System.out.println("Введите пароль:");
        String pass = SCANNER.next();
        HashMap<String, Customer> customersColl = CS.getCustomers();
        Customer customer;
        if (customersColl.containsKey(login)) {
            customer = customersColl.get(login);
            if (!customer.checkPassword(pass)) {
                System.out.println("Неверные логин или пароль");
                login();
            }
            currentUser = customer;
        }
        else {
            System.out.println("Неверные логин или пароль");
            login();
        }
    }

    private void purchaseMenu() {
        while (SCANNER.hasNextLine()) {
            printGreetingPurchase();
            String result = SCANNER.nextLine();

            HashMap<Integer, Product> allProducts, orderProducts;
            allProducts = PS.getProducts();
            orderProducts = new HashMap<>();

            Pattern p = Pattern.compile("(\\b\\d+\\b)+");
            Matcher matcher = p.matcher(result);
            Integer temp;
            int i = 1;
            while (matcher.find()) {
                temp = new Integer(matcher.group());
                if (allProducts.containsKey(temp)) {
                    orderProducts.put(i++, allProducts.get(temp));
                }
            }
            printProducts(orderProducts);
        }
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

    public void printGreetingPurchase() {
        System.out.println("Укажите номера товаров через пробел, если необходимо купить несколько одинаковых товаров," +
                " просто повторите его номер нужное количество раз");
    }

    private void printProducts(HashMap<Integer, Product> products) {
        products = products == null ? PS.getProducts() : products;
        for (HashMap.Entry<Integer, Product> item : products.entrySet()) {
            System.out.println(item.getKey()+". "+item.getValue());
        }
    }

    private void exit() {
        SCANNER.close();
        System.exit(0);
    }

}
