package local;

import local.menu.model.Menu;
import local.store.ProductType;

public class Main {

  public static void main(String[] args) {

    Menu menu = new Menu(ProductType.FOOD);
    menu.start();

  }

}
