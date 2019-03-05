package checkout;

import java.time.LocalDate;

public class FactorByCategoryOffer extends Offer {
    public FactorByCategoryOffer(LocalDate expirationDate, Category category, int factor) {
        super(expirationDate, new Reward_Factor(category, factor), new Condition_ByCategory(category));
    }
}
