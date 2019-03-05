package checkout;

import java.time.LocalDate;

public class FactorByCategoryOffer extends Offer {
    public FactorByCategoryOffer(LocalDate expirationDate, Category category, int factor) {
        super(expirationDate, new FactorReward(category, factor), new ByCategoryCondition(category));
    }
}
