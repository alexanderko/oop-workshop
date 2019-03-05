package checkout;

import java.time.LocalDate;

public class FactorByCategoryOffer extends Offer {
    final Category category;
    final int factor;

    public FactorByCategoryOffer(Category category, int factor) {
        this.category = category;
        this.factor = factor;
        this.expirationDate = LocalDate.of(2018, 1,1);
    }

    @Override
    public void apply(Check check) {
        int points = check.getCostByCategory(this.category);
        check.addPoints(points * (this.factor - 1));
    }

    @Override
    public boolean isOfferavAilable() {
        if (this.expirationDate.isAfter(todayDate))
            return true;
        else return false;
    }

}
