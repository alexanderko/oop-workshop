package checkout;

import java.time.LocalDate;

public class FactorByCategoryOffer extends Offer {
    final Category category;
    final int factor;
    final LocalDate expiredDate;

    public FactorByCategoryOffer(Category category, int factor) {
        this(category, factor, null);
    }

    public FactorByCategoryOffer(Category category, int factor, LocalDate expiredDate) {
        this.expiredDate = expiredDate;
        this.category = category;
        this.factor = factor;
    }

    @Override
    public void apply(Check check) {
        int points = check.getCostByCategory(category);
        check.addPoints(points * (factor - 1));
    }

    @Override
    public boolean isExpired(LocalDate currentDate) {
        if (expiredDate == null) {
            return false;
        } else {
            return currentDate.isAfter(expiredDate);
        }
    }
}
