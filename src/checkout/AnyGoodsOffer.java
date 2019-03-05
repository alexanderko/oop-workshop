package checkout;

import java.util.Date;

public class AnyGoodsOffer extends Offer {
    public final int totalCost;
    public final int points;
    private final Date expirationDate;

    public AnyGoodsOffer(int totalCost, int points) {
        this.totalCost = totalCost;
        this.points = points;
        this.expirationDate = null;
    }

    public AnyGoodsOffer(int totalCost, int points, Date expirationDate) {
        this.totalCost = totalCost;
        this.points = points;
        this.expirationDate = expirationDate;
    }

    @Override
    public void apply(Check check) {
        AnyGoodsOffer agOffer = (AnyGoodsOffer) this;
        if (agOffer.totalCost <= check.getTotalCost())
            check.addPoints(agOffer.points);
    }

    @Override
    public boolean isActual(){
        return true;
    }
}
