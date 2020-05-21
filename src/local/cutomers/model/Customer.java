package local.cutomers.model;

import java.util.*;

public class Customer {

    private static ArrayList<Customer> Users;

    private String login;
    private String password;

    public Customer(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public boolean checkLogin(String log) {
        return login.equals(log);
    }

    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }

}
