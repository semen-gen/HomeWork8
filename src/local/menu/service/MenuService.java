package local.menu.service;

import local.cutomers.model.Customer;
import local.cutomers.service.CustomerService;
import local.exeptions.PaymentErrors;
import local.exeptions.WrongProductID;
import local.menu.model.Menu;
import local.order.model.Order;
import local.order.service.OrderService;
import local.store.ProductType;
import local.store.model.Product;
import local.store.service.ProductService;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuService {

    private final Menu MENU;
    private final Scanner SCANNER;
    private final CustomerService CS;
    private final ProductService PS;
    private final String customerFile;

    private Customer currentUser;
    private Order order;

    {
        customerFile = "./res/customers.txt";
    }

    public MenuService(Menu menu, ProductType type) {
        MENU = menu;
        SCANNER = new Scanner(System.in);
        CS = new CustomerService(customerFile);
        PS = new ProductService(type, type.getProductFile());
    }

    public void mainMenu() {
        cycle:
        {
            while (SCANNER.hasNext()) {
                if (SCANNER.hasNextInt()) {
                    switch (SCANNER.nextInt()) {
                        case 1:
                            MENU.printLoginItems();
                            break;
                        case 2:
                            break cycle;
                        default:
                            System.out.println("Выбрано неверное действие");
                            break;
                    }
                }
                else {
                    System.out.println("Недопустимые символы: " + SCANNER.next());
                }
            }
        }
        exit();
    }

    public void login() {
        while (true) {
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
                    continue;
                }
                currentUser = customer;
                order = new Order(currentUser);
                MENU.printStoreItems();
            }
            else {
                System.out.println("Неверные логин или пароль");
            }
        }
    }

    public void storeMenu() {
        store:
        {
            while (SCANNER.hasNext()) {
                if (SCANNER.hasNextInt()) {
                    switch (SCANNER.nextInt()) {
                        case 1:
                            PS.printProducts(null);
                            break;
                        case 2:
                            MENU.printGreetingPurchase();
                            break;
                        case 3:
                            break store;
                        default:
                            System.out.println("Выбрано неверное действие");
                            break;
                    }
                }
                else {
                    System.out.println("Недопустимые символы: " + SCANNER.next());
                }
            }
        }
        exit();
    }

    public void purchaseMenu() {
        SCANNER.skip("\\n");
        while (SCANNER.hasNext()) {
            String result = SCANNER.nextLine();
            checkIDForString(result);
            checkIdForBad(result);
            if (order.count() > 0) MENU.printOrderMenu(order, PS);
        }
    }

    public void orderMenu() {
        while (SCANNER.hasNext()) {
            if (SCANNER.hasNextInt()) {
                int key = SCANNER.nextInt();

                switch (key) {
                    case 1:
                        OrderService os = new OrderService(order);
                        try {
                            os.tryPay();
                        } catch (PaymentErrors paymentErrors) {
                            paymentErrors.printMessage();
                        }
                        MENU.printStoreItems();
                        break;
                    case 2:
                        MENU.printGreetingPurchase();
                        break;
                    default:
                        System.out.println("Вы указали неправильный символ " + key);
                }
            }
            else {
                System.out.println("Вы указали неправильный символ " + SCANNER.next());
            }
        }
    }

    private void checkIDForString(String result) {
        Pattern notDecimals = Pattern.compile("\\b[^\\d\\s]\\w+\\b|\\b\\w+[^\\d\\s]\\b|\\b\\d+[^\\d\\s]+\\d+\\b");
        Matcher matcher = notDecimals.matcher(result);
        while (matcher.find()) {
            try {
                throw new WrongProductID(matcher.group());
            } catch (WrongProductID wrongProductID) {
                wrongProductID.printMessage();
            }
        }
    }

    private void checkIdForBad(String result) {
        HashMap<Integer, Product> allProducts = PS.getProducts();
        Pattern decimals = Pattern.compile("(\\b\\d+\\b)+");
        Matcher matcher = decimals.matcher(result);
        Integer temp;

        while (matcher.find()) {
            temp = new Integer(matcher.group());
            if (allProducts.containsKey(temp)) {
                order.addItem(allProducts.get(temp));
            }
            else {
                try {
                    throw new WrongProductID(temp);
                } catch (WrongProductID wrongProductID) {
                    wrongProductID.printMessage();
                }
            }
        }
    }

    private void exit() {
        SCANNER.close();
        System.exit(0);
    }

}
