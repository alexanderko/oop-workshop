package checkout;

import java.time.LocalDate;

public class AnyGoodsOffer extends Offer {
    public final int totalCost;
    public final int points;
    private final LocalDate expirationDate;

    public AnyGoodsOffer(int totalCost, int points) {
        this.totalCost = totalCost;
        this.points = points;
        this.expirationDate = null;
    }

    public AnyGoodsOffer(int totalCost, int points, LocalDate expirationDate) {
        this.totalCost = totalCost;
        this.points = points;
        this.expirationDate = expirationDate;
    }

    @Override
    public void apply(Check check) {
        if (isActual()) {
            AnyGoodsOffer agOffer = (AnyGoodsOffer) this;
            if (agOffer.totalCost <= check.getTotalCost())
                check.addPoints(agOffer.points);
        }
    }

    @Override
    public boolean isActual(){
        if (this.expirationDate != null) {
            return expirationDate.isAfter(LocalDate.now());
        } else {
            return true;
        }
    }
}
