package checkout;

public class FactorReward implements Reward {

    private final  Category category;
    private final int factor;

    public FactorReward(Category category, int factor) {
        this.category = category;
        this.factor = factor;
    }

    @Override
    public void applyReward(Check check) {
        int points = check.getCostByCategory(this.category);
        check.addPoints(points * (this.factor - 1));
    }
}
