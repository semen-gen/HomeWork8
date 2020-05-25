package local.store;

public enum ProductType {
    FOOD("Еда", "src\\local\\data\\productsFood.txt"),
    CARS("Машины", "src\\local\\data\\productsCars.txt"),
    ANIMALS("Животные", "src\\local\\data\\productsAnimals.txt"),
    ELECTRONICS("Электроника", "src\\local\\data\\productsElectronics.txt");

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
