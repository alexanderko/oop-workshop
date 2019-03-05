package checkout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Check {
    private List<Product> products = new ArrayList<>();
    private ArrayList<Offer> offers = new ArrayList<>();
    private LocalDate date;
    private int points = 0;

    public Check(LocalDate date) {
        this.date = date;
    }

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

    void addOffer(Offer offer) {
        offers.add(offer);
    }

    void applyOffers() {
        for (int i = 0; i < offers.size(); i++) {
            offers.get(i).applyOffer(this, date);
            //offer.applyOffer(check, currentDate);
        }
    }

    int getCostByCategory(Category category) {
        return products.stream()
                .filter(p -> p.category == category)
                .mapToInt(p -> p.price)
                .reduce(0, (a, b) -> a + b);
    }
}
