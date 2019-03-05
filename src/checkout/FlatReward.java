package checkout;

public class FlatReward implements Reward {
    public final int totalCost;
    public final int points;

    public FlatReward(int totalCost, int points) {
        this.totalCost = totalCost;
        this.points = points;
    }

    @Override
    public void applyReward(Check check) {
        check.addPoints(this.points);
    }
}
