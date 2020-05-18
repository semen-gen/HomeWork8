package local.store;

public enum ProductType {
    FOOD("Еда"),
    CARS("Машины"),
    ANIMALS("Животные"),
    ELECTRONIC("Электроника");

    private final String NAME;

    ProductType(String name) {
        NAME = name;
    }

    public String getNAME() {
        return NAME;
    }
}
