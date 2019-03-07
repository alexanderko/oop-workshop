package checkout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Check {
    private List<Product> products = new ArrayList<>();
    private int points = 0;
    private int discount = 0;
    private static LocalDate todayDate = LocalDate.now();

    public int getTotalCost() {
        int totalCost = 0;
        for (Product product : this.products) {
            totalCost += product.price;
        }
        return totalCost;
    }

    public int getTotalCostWithDiscount() {
        return getTotalCost() - discount/10;
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

    public int getPointsForBrand(Brand brand) {
        return products.stream()
                .filter(p -> p.brand == brand)
                .mapToInt(p -> p.price)
                .reduce(0, (a,b)-> a+b);
    }

    public void addDiscount(int discount) {
        this.discount = discount;
    }

    public List<Product> getProducts() {
        return products;
    }

    public static LocalDate getTodayDate() {
        return todayDate;
    }
}
