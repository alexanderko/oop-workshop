package checkout;

import java.time.LocalDate;

public class DiscountByBrandOffer extends Offer {
    public DiscountByBrandOffer(LocalDate expirationDate, Trademark trademark, int discount) {
        super(expirationDate, new DiscountReward(discount), new ByTrademarksCondition(trademark));
    }
}
