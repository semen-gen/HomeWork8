package local.menu.model;

import local.menu.service.MenuService;

public class Menu {

    public void start() {
        MenuService ms = new MenuService();
        ms.mainMenu();
    }

}
