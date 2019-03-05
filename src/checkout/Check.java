package checkout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Check {
    private List<Product> products = new ArrayList<>();
    private ArrayList<Offer> offers = new ArrayList<>();
    private LocalDate date;
    private int points = 0;
    private int discount = 0;

    public Check(LocalDate date) {
        this.date = date;
    }

    public int getTotalCost() {
        int totalCost = 0;
        for (Product product : this.products) {
            totalCost += product.price;
        }
        totalCost -= discount;
        return totalCost;
    }

    void addProduct(Product product) {
        products.add(product);
    }

    public int getTotalPoints() {
        return getTotalCost() + points;
    }

    void addDiscount(int discount) {
        this.discount += discount;
    }

    void addPoints(int points) {
        this.points += points;
    }

    void addOffer(Offer offer) {
        offers.add(offer);
    }

    void applyOffers() {
        for (Offer offer : offers) {
            offer.applyOffer(this, date);
        }
    }

    int getCostByCategory(Category category) {
        return products.stream()
                .filter(p -> p.category == category)
                .mapToInt(p -> p.price)
                .reduce(0, (a, b) -> a + b);
    }

    boolean whetherHaveBrand(Brand brand) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).brand == brand)
                return true;
        }
        return false;
    }
}
