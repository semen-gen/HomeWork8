package local.order.service;

import local.exeptions.PaymentErrors;
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
  private final Order ORDER;

  {
    ORDERS_DIR = "./res/orders/";
  }

  public OrderService(Order order) {
    this.ORDER = order;
  }

  public void tryPay() throws PaymentErrors {
    double sum = ORDER.getOrderSum();

    if (ORDER.count() == 0) {
      throw new PaymentErrors("Нечего оплачивать. нет выбранных товаров.");
    } else if (ORDER.getOrderCustomer().getCash() < sum) {
      throw new PaymentErrors("Недостаточно средств.");
    } else {
      ORDER.getOrderCustomer().pay(sum);
      saveOrder();
      System.out.println("Заказ успешно оплачен");
      System.out.println(ORDER.listOrderItems());
      ORDER.clearOrder();
    }
  }

  private void saveOrder() {
    StringBuffer fileName = new StringBuffer();
    Calendar time = Calendar.getInstance();

    fileName.append(ORDERS_DIR).append(ORDER.getOrderCustomer().getLogin());
    new SimpleDateFormat("-y-MM-dd-").format(time.getTime(), fileName, new FieldPosition(0));
    fileName.append(time.getTimeInMillis()).append(".txt");

    File file = new File(fileName.toString());
    try (FileWriter fw = new FileWriter(file)) {
      BufferedWriter bf = new BufferedWriter(fw);
      bf.append("Заказ пользователя ").append(ORDER.getOrderCustomer().getLogin());
      bf.newLine();
      bf.append("На сумму: ").append(String.valueOf(ORDER.getOrderSum()));
      bf.newLine();
      bf.append("Список товаров:");
      bf.newLine();
      bf.append(ORDER.listOrderItems());
      bf.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
