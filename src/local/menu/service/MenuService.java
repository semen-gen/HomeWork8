package local.menu.service;

import local.cutomers.model.Customer;
import local.cutomers.service.CustomerService;
import local.order.model.Order;
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
    private final String productFile;
    private final String customerFile;

    private Customer currentUser;
    private Order order;

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
            order = new Order(currentUser);
        }
        else {
            System.out.println("Неверные логин или пароль");
            login();
        }
    }

    private void purchaseMenu() {
        printGreetingPurchase();
        SCANNER.skip("\\n");
        while (SCANNER.hasNext()) {
            String result = SCANNER.nextLine();

            HashMap<Integer, Product> allProducts = PS.getProducts();

            Pattern p = Pattern.compile("(\\b\\d+\\b)+");
            Matcher matcher = p.matcher(result);
            Integer temp;

            while (matcher.find()) {
                temp = new Integer(matcher.group());
                if (allProducts.containsKey(temp)) {
                    order.addItem(allProducts.get(temp));
                }
            }
            if (order.count() > 0) break;
            else printGreetingPurchase();

        }
        paymentMenu();
    }

    private void paymentMenu() {
        printPaymentMenu();

        while (SCANNER.hasNext()) {
            if (SCANNER.hasNextInt()) {
                int key = SCANNER.nextInt();

                switch (key) {
                    case 1:
                        // реализовать логику оплаты и сохранения заказа
                        break;
                    case 2:
                        storeMenu();
                        break;
                    default:
                        System.out.println("Вы указали неправильный символ " + key);
                        paymentMenu();
                }
            }
            else {
                System.out.println("Вы указали неправильный символ " + SCANNER.next());
                printPaymentMenu();
            }
        }
    }

    private void printPaymentMenu() {
        System.out.println("Товары в заказе:");
        printProducts(order.getItems());
        System.out.println();
        System.out.println("Хотите продолжить покупки или оплатить?");
        System.out.println("1. Оплатить");
        System.out.println("2. Продолжить покупки");
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
            System.out.println(item.getKey() + ". " + item.getValue());
        }
    }

    private void exit() {
        SCANNER.close();
        System.exit(0);
    }

}
