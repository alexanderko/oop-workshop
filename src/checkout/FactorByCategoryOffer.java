package checkout;

import java.util.Date;

public class FactorByCategoryOffer extends Offer {
    final Category category;
    final int factor;
    private final Date expirationDate;

    public FactorByCategoryOffer(Category category, int factor) {
        this.category = category;
        this.factor = factor;
        this.expirationDate = null;
    }

    public FactorByCategoryOffer(Category category, int factor, Date expirationDate) {
        this.category = category;
        this.factor = factor;
        this.expirationDate = expirationDate;
    }

    @Override
    public void apply(Check check) {
        FactorByCategoryOffer fbOffer = (FactorByCategoryOffer) this;
        int points = check.getCostByCategory(fbOffer.category);
        check.addPoints(points * (fbOffer.factor - 1));
    }

    @Override
    public boolean isActual(){
        return true;
    }
}
