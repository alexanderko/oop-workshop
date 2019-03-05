package checkout;

import java.time.LocalDate;

public class AnyGoodsOffer extends Offer {

    public AnyGoodsOffer(LocalDate expirationDate, int totalCost, int points) {
        super(expirationDate, new Reward_Flat(totalCost, points), new Condition_TotalCost(totalCost));
    }
}
