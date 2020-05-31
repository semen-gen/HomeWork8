package local.store;

public enum ProductType {
  FOOD("Еда", "./res/productsFood.txt"),
  CARS("Машины", "./res/productsCars.txt"),
  ANIMALS("Животные", "./res/productsAnimals.txt"),
  ELECTRONICS("Электроника", "./res/productsElectronics.txt");

  String name;
  String productFile;

  ProductType(String name, String src) {
    this.name = name;
    productFile = src;
  }

  public String getName() {
    return name;
  }

  public String getProductFile() {
    return productFile;
  }
}
