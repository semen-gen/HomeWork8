package local.cutomers.model;

public class Customer {

    private final String LOGIN;
    private final String PASSWORD;
    private int cash;

    public Customer(String login, String password, int cash) {
        LOGIN = login;
        PASSWORD = password;
        this.cash = cash;
    }

    public boolean checkLogin(String log) {
        return LOGIN.equals(log);
    }

    public boolean checkPassword(String pass) {
        return PASSWORD.equals(pass);
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public String getLogin() {
        return LOGIN;
    }

}
