package checkout;

import java.util.ArrayList;
import java.util.List;

public class Check {
    private List<Product> products = new ArrayList<>();
    private List<Offer> offers = new ArrayList<>();
    private int points = 0;
    private double discount = 0;
    public int getTotalPrice() {
        int totalPrice = 0;
        for (Product product : this.products) {
            totalPrice += product.price;
        }
        return totalPrice;
    }
    public double getTotalCost() {
        double totalCost = 0;
        totalCost += getTotalPrice();
        totalCost -= discount;
        return totalCost;
    }
    void addProduct(Product product) {
        products.add(product);
    }

    public int getTotalPoints() {
        return getTotalPrice() + points;
    }

    void addPoints(int points) {
        this.points += points;
    }

    void useOffers(Check check) {
        for (Offer offer: offers) {
            offer.apply(check);
        }
    }

    void addOffer(Offer offer) {
        offers.add(offer);
    }

    int getCostByCategory(Category category) {
        return products.stream()
                .filter(p -> p.category == category)
                .mapToInt(p -> p.price)
                .reduce(0, (a, b) -> a + b);
    }

    void addDiscount(double discount) {
        this.discount += discount;
    }
}
