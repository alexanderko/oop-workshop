package checkout;

import java.time.LocalDate;

public class AnyGoodsOffer extends Offer {
    public final int totalCost;
    public final int points;

    public AnyGoodsOffer(int totalCost, int points, LocalDate expiredDate) {
        super(expiredDate);
        this.totalCost = totalCost;
        this.points = points;
    }

    @Override
    public void applyAfterCheck(Check check) {
        if (totalCost <= check.getTotalCost())
            check.addPoints(points);
    }
}
