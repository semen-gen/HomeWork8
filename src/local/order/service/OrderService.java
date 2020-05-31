package local.order.service;

import jdk.nashorn.internal.parser.DateParser;
import local.cutomers.model.Customer;
import local.order.model.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OrderService {

    private final String ORDERS_DIR;
    private Order order;
    private Customer customer;

    {
        ORDERS_DIR = "./res/orders/";
    }

    public OrderService(Order order, Customer customer) {
        this.order = order;
        this.customer = customer;
    }

    public void tryPay() {
        double sum = order.getSum();
        if (customer.getCash() >= sum) {
            customer.pay(sum);
            saveOrder();
            order = null;
            System.out.println(customer);
            System.out.println(order);
        }
    }

    private void saveOrder() {
        StringBuffer fileName = new StringBuffer();
        fileName.append(ORDERS_DIR).append(customer.getLogin());
        Calendar time = Calendar.getInstance();
        new SimpleDateFormat("-y-MM-dd-").format(time.getTime(), fileName, new FieldPosition(0));
        fileName.append(time.getTimeInMillis()).append(".txt");
        File file = new File(fileName.toString());
        try (FileWriter fw = new FileWriter(file)) {
            BufferedWriter bf = new BufferedWriter(fw);
            bf.append("Заказ пользователя ").append(customer.getLogin());
            bf.newLine();
            bf.append("На сумму: ").append(String.valueOf(order.getSum()));
            bf.newLine();
            bf.append("Список товаров:");
            bf.newLine();
            bf.flush();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
