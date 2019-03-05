package checkout;

public class FactorByCategoryOffer extends Offer {
    final Category category;
    final int factor;

    public FactorByCategoryOffer(Category category, int factor) {
        this.category = category;
        this.factor = factor;
    }

    @Override
    public void apply(Check check) {
        if (this instanceof FactorByCategoryOffer) {
            FactorByCategoryOffer fbOffer = (FactorByCategoryOffer) this;
            int points = check.getCostByCategory(fbOffer.category);
            check.addPoints(points * (fbOffer.factor - 1));
        }
    }
}
