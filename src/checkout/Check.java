package checkout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class Check {
    private List<Product> products = new ArrayList<>();
    private List<Offer> offers = new ArrayList<>();
    private Map<Integer, Predicate<Product>> discounts = new HashMap<>();
    private int points = 0;

    public int getTotalCost() {
        int totalCost = 0;
        for (Product product : this.products) {
            totalCost += product.price;
        }
        return calculateDiscounts(totalCost);
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


    int getCostBy(Predicate<Product> predicate) {
        return products.stream()
                .filter(predicate)
                .mapToInt(p -> p.price)
                .reduce(0, (a, b) -> a + b);
    }

    void addOffer(Offer offer) {
        offers.add(offer);
    }

    void applyOffers() {
        LocalDate currentDate = LocalDate.now();
        offers.stream()
                .filter(offer -> !offer.isExpired(currentDate))
                .forEach(offer -> offer.apply(this));
    }

    List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    void addDiscountFor(int percent, Predicate<Product> predicate) {
        discounts.put(percent, predicate);
    }

    private int calculateDiscounts(int totalCost) {
        AtomicInteger totalCostAtomic = new AtomicInteger(totalCost);
        discounts.forEach((percent, predicate) -> totalCostAtomic.addAndGet(- getCostBy(predicate) * percent / 100));
        return totalCostAtomic.get();
    }
}
