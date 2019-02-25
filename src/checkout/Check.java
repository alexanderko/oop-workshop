package checkout;

import java.util.ArrayList;
import java.util.List;

public class Check {
    private List<Product> products = new ArrayList<>();

    public int getTotalCost() {
        int totalCost = 0;
        for (Product product : this.products) {
            totalCost += product.price;
        }
        return totalCost;
    }

    void addProduct(Product product) {
        products.add(product);
    }


}
