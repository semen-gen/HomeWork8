package local.cutomers.service;

import local.cutomers.model.Customers;

import java.io.*;
import java.util.*;

public class CustomerService {

    private final File FILE;
    private LinkedList<Customers> customers;

    public CustomerService(String file) {
        FILE = new File(file);
    }

    public LinkedList<Customers> getCustomers() {
        if (customers == null) {
            customers = new LinkedList<>();
            ArrayList<String> data = getFileData();
            Customers customer;
            String[] temp;

            for (String item : data) {
                temp = item.split(",");
                customer = new Customers(temp[0], temp[1], new Integer(temp[2]));
                customers.push(customer);
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

}
