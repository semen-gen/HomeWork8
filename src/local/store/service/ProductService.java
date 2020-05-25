package local.store.service;

import local.store.ProductType;
import local.store.model.*;

import java.io.*;
import java.util.*;

public class ProductService {

    private final File FILE;
    private final ProductType TYPE;
    private HashMap<Integer, Product> products;

    public ProductService(ProductType type, String file) {
        TYPE = type;
        FILE = new File(file);
    }

    public HashMap<Integer, Product> getProducts() {
        if (products == null) {
            products = new HashMap<>();
            ArrayList<String> data = getFileData();
            Product product;
            String[] tmp;
            for (String item : data) {
                tmp = item.split(",");
                switch (TYPE) {
                    case FOOD:
                        product = new Food(tmp[0], new Double(tmp[1]));
                        break;
                    case CARS:
                        product = new Car(tmp[0], new Double(tmp[1]));
                        break;
                    case ANIMALS:
                        product = new Animal(tmp[0], new Double(tmp[1]));
                        break;
                    case ELECTRONICS:
                        product = new Electronic(tmp[0], new Double(tmp[1]));
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + TYPE);
                }
                products.put(product.getID(), product);
            }
        }
        return products;
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
                    String line;
                    while ((line = br.readLine()) != null) {
                        data.add(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public void printProducts(HashMap<Integer, Product> products) {
        products = products == null ? getProducts() : products;
        for (HashMap.Entry<Integer, Product> item : products.entrySet()) {
            System.out.println(item.getKey() + ". " + item.getValue());
        }
    }

}
