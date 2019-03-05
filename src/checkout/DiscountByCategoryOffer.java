package checkout;

import java.time.LocalDate;

public class DiscountByCategoryOffer extends Offer {

    public DiscountByCategoryOffer(LocalDate expirationDate, Category category, int discount) {
        super(expirationDate, new DiscountReward(discount), new ByCategoryCondition(category));
    }
}
