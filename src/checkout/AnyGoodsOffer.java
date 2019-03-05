package checkout;

import java.time.LocalDate;

public class AnyGoodsOffer extends Offer {
    final int totalCost;
    final int points;
    final LocalDate expirationDate;

    public AnyGoodsOffer(int totalCost, int points, LocalDate expirationDate) {
        this.totalCost = totalCost;
        this.points = points;
        this.expirationDate = expirationDate;
    }

    public AnyGoodsOffer(int totalCost, int points) {
        this(totalCost, points, null);
    }

    @Override
    public void apply(Check check) {
        if (isActual()) {
            if (this.totalCost <= check.getTotalCost())
                check.addPoints(this.points);
        }
    }
}
