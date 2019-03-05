package checkout;

public class Reward_Factor implements Reward {

    private final  Category category;
    private final int factor;

    public Reward_Factor(Category category, int factor) {
        this.category = category;
        this.factor = factor;
    }

    @Override
    public void applyReward(Check check) {
        int points = check.getCostByCategory(this.category);
        check.addPoints(points * (this.factor - 1));
    }
}
