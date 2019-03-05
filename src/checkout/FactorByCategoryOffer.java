package checkout;

import java.time.LocalDate;

public class FactorByCategoryOffer extends Offer {
    final Category category;
    final int factor;
    private final LocalDate expirationDate;

    public FactorByCategoryOffer(Category category, int factor) {
        this.category = category;
        this.factor = factor;
        this.expirationDate = null;
    }

    public FactorByCategoryOffer(Category category, int factor, LocalDate expirationDate) {
        this.category = category;
        this.factor = factor;
        this.expirationDate = expirationDate;
    }

    @Override
    public void apply(Check check) {
        if (isActual()){
            FactorByCategoryOffer fbOffer = (FactorByCategoryOffer) this;
            int points = check.getCostByCategory(fbOffer.category);
            check.addPoints(points * (fbOffer.factor - 1));
        }
    }

    @Override
    public boolean isActual(){
        if (this.expirationDate != null) {
            return expirationDate.isAfter(LocalDate.now());
        } else {
            return true;
        }
    }
}
