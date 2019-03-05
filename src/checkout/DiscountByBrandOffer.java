package checkout;

import java.time.LocalDate;

public class DiscountByBrandOffer extends Offer {
    public DiscountByBrandOffer(LocalDate expirationDate, Brand brand, int discount) {
        super(expirationDate, new Reward_Discount(discount), new Condition_ByBrand(brand));
    }
}
