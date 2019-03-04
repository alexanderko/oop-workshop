package checkout;

import java.util.ArrayList;
import java.util.List;

public class Check {
    private List<Product> products = new ArrayList<>();
    private int points = 0;
    private int discount = 0;

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

    public int getTotalPoints() {
        return getTotalCost() + points;
    }

    void addPoints(int points) {
        this.points += points;
    }

    int getCostByCategory(Category category) {
        return products.stream()
                .filter(p -> p.category == category)
                .mapToInt(p -> p.price)
                .reduce(0, (a, b) -> a + b);
    }

    int getCostByTrademark(Trademark trademark) {
        return products.stream()
                .filter(p -> p.trademark == trademark)
                .mapToInt(p -> p.price)
                .reduce(0, (a, b) -> a + b);
    }

    void addDiscount(int discount) {
        this.discount += discount;
    }

    public int getDiscount() {
        return discount;
    }
}
