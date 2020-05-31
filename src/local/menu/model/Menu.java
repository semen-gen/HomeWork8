package local.menu.model;

import local.menu.service.MenuService;
import local.order.model.Order;
import local.store.ProductType;
import local.store.service.ProductService;

public class Menu {

    private final MenuService MS;

    public Menu(ProductType type) {
        MS = new MenuService(this, type);
    }

    public void start() {
        printMainMenuItems();
    }

    public void printMainMenuItems() {
        System.out.println("Главное меню");
        System.out.println("============");
        System.out.println("1. Авторизоваться");
        System.out.println("2. Выйти");
        MS.mainMenu();
    }

    public void printLoginItems() {
        System.out.println("Авторизация");
        System.out.println("===========");
        MS.login();
    }

    public void printStoreItems() {
        System.out.println("Меню магазина");
        System.out.println("=============");
        System.out.println("1. Список товара");
        System.out.println("2. Совершить покупки");
        System.out.println("3. Выйти");
        MS.storeMenu();
    }

    public void printGreetingPurchase() {
        System.out.println("Укажите номера товаров через пробел, если необходимо купить несколько одинаковых товаров," +
                " просто повторите его номер нужное количество раз");
        MS.purchaseMenu();
    }

    public void printOrderMenu(Order order, ProductService PS) {
        System.out.println("Товары в заказе:");
        PS.printProducts(order.getOrderItems());
        System.out.println();
        System.out.println("Хотите продолжить покупки или оплатить?");
        System.out.println("1. Оплатить");
        System.out.println("2. Продолжить покупки");
        MS.orderMenu();
    }

}
