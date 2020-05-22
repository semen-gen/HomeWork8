package local.cutomers.service;

import local.cutomers.model.Customers;

import java.io.*;
import java.util.*;

public class CustomerService {

    private final File FILE;
    private HashMap<String, Customers> customers;

    public CustomerService(String file) {
        FILE = new File(file);
    }

    public HashMap<String, Customers> getCustomers() {
        if (customers == null) {
            customers = new HashMap<>();
            ArrayList<String> data = getFileData();
            Customers customer;
            String[] temp;

            for (String item : data) {
                temp = item.split(",");
                customer = new Customers(temp[0], temp[1], new Integer(temp[2]));
                customers.put(customer.getLogin(), customer);
            }
        }
        return customers;
    }

    private ArrayList<String> getFileData() {
        ArrayList<String> data = new ArrayList<>();
        try {
            if (FILE.createNewFile()) {
                return data;
            }
            else {
                try (FileReader fr = new FileReader(FILE)) {
                    BufferedReader br = new BufferedReader(fr);
                    String str;
                    while ((str = br.readLine()) != null) {
                        data.add(str);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public Customers login(Scanner scanner) {
        System.out.println("Авторизация");
        System.out.println("===========");
        System.out.println("Введите логин:");
        String login = scanner.next();
        System.out.println("Введите пароль:");
        String pass = scanner.next();
        HashMap<String, Customers> customersColl = getCustomers();
        Customers customer = null;
        if (customersColl.containsKey(login)) {
            customer = customersColl.get(login);
            if (!customer.checkPassword(pass)) {
                System.out.println("Неверные логин или пароль");
                login(scanner);
            }
        }
        else {
            System.out.println("Неверные логин или пароль");
            login(scanner);
        }
        return customer;
    }

}
