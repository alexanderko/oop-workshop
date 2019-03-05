package checkout;

import java.time.LocalDate;

public class FactorByCategoryOffer extends Offer {
    public Category category;
    public int factor;
    public LocalDate expirationDate;


    public FactorByCategoryOffer(Category category, int factor, LocalDate expirationDate) {
        this.category = category;
        this.factor = factor;
        this.expirationDate = expirationDate;
    }

    public FactorByCategoryOffer(Category category, int factor) {
            this(category, factor, null);
    }

    @Override
    public void apply(Check check) {
        if (isActual()){
            int points = check.getCostByCategory(this.category);
            check.addPoints(points * (this.factor - 1));
        }
    }
}
