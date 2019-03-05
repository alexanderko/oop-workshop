package checkout;

import java.time.LocalDate;

public class AnyGoodsOffer extends Offer {

    public AnyGoodsOffer(LocalDate expirationDate, int totalCost, int points) {
        super(expirationDate, new FlatReward(totalCost, points), new TotalCostCondition(totalCost));
    }
}
