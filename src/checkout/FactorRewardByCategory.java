package checkout;

public class FactorRewardByCategory implements Reward {
    private int factor;
    Category category;

    public FactorRewardByCategory(int factor, Category category) {
        setCategory(category);
        setFactor(factor);
    }

    @Override
    public void apply(Check check) {
        int points = check.getCostByCategory(category);
        check.addPoints(points * (factor-1));
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
