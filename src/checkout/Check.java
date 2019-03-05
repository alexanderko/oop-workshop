package checkout;

import java.util.ArrayList;
import java.util.List;

public class Check {
    private List<Product> products = new ArrayList<>();
    private List<Offer> offers = new ArrayList<>();
    private int points = 0;

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

    void addOffer(Offer offer){
        this.offers.add(offer);
    }

    void useOffers(){
        for (Offer offer:this.offers){
            offer.apply(this);
        }
    }

    int getCostByCategory(Category category) {
        return products.stream()
                .filter(p -> p.category == category)
                .mapToInt(p -> p.price)
                .reduce(0, (a, b) -> a + b);
    }


}
