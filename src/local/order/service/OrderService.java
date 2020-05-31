package local.order.service;

import local.exeptions.PaymentErrors;
import local.order.model.Order;
import local.store.model.Product;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class OrderService {

    private final String ORDERS_DIR;
    private Order order;

    {
        ORDERS_DIR = "./res/orders/";
    }

    public OrderService(Order order) {
        this.order = order;
    }

    public void tryPay() throws PaymentErrors {
        double sum = order.getOrderSum();

        if (order.count() == 0) {
            throw new PaymentErrors("Нечего оплачивать. нет выбранных товаров.");
        }
        else if (order.getOrderCustomer().getCash() < sum) {
            throw new PaymentErrors("Недостаточно средств.");
        }
        else {
            order.getOrderCustomer().pay(sum);
            saveOrder();
            System.out.println("Заказ успешно оплачен");
            System.out.println(order.listOrderItems());
            order.clearOrder();
        }
    }

    private void saveOrder() {
        StringBuffer fileName = new StringBuffer();
        Calendar time = Calendar.getInstance();

        fileName.append(ORDERS_DIR).append(order.getOrderCustomer().getLogin());
        new SimpleDateFormat("-y-MM-dd-").format(time.getTime(), fileName, new FieldPosition(0));
        fileName.append(time.getTimeInMillis()).append(".txt");

        File file = new File(fileName.toString());
        try (FileWriter fw = new FileWriter(file)) {
            BufferedWriter bf = new BufferedWriter(fw);
            bf.append("Заказ пользователя ").append(order.getOrderCustomer().getLogin());
            bf.newLine();
            bf.append("На сумму: ").append(String.valueOf(order.getOrderSum()));
            bf.newLine();
            bf.append("Список товаров:");
            bf.newLine();
            bf.append(order.listOrderItems());
            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
