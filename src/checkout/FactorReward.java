package checkout;

public class FactorReward implements Reward {
    int factor;

    public FactorReward(int factor) {
        this.factor = factor;
    }

    public void applyReward(Check check, int cost) {
        check.addPoints(cost * (factor - 1));
    }
}
