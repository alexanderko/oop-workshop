package checkout;

import java.time.LocalDate;

public class AnyGoodsOffer extends Offer {
    public final int totalCost;
    public final int points;

    public AnyGoodsOffer(int totalCost, int points, LocalDate expirationDate) {
        super(expirationDate);
        this.totalCost = totalCost;
        this.points = points;
    }

    @Override
    public boolean isSatisfiedConditionals(Check check) {
        return true;
    }

    @Override
    public void applyReward(Check check) {
        if (this.totalCost <= check.getTotalCost())
            check.addPoints(this.points);
    }
}
