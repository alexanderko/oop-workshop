package checkout;

public class FactorByCategoryOffer extends Offer {
    final Category category;
    final int factor;

    public FactorByCategoryOffer(Category category, int factor) {
        this.category = category;
        this.factor = factor;
    }

    @Override
    public boolean isSatisfiedConditionals(Check check) {
        return true;
    }

    @Override
    public void applyReward(Check check) {
        int points = check.getCostByCategory(this.category);
        check.addPoints(points * (this.factor - 1));
    }
}
