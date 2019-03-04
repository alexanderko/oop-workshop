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
        int points = check.getCostByCategory(category);
        check.addPoints(points * (factor - 1));
    }
}
