package checkout;

public class Factor implements Reward {
    private int factor;

    public Factor(int factor) {
        this.factor = factor;
    }

    @Override
    public void useReward(Check check, Condition condition) {
        int points = condition.getPointsByCondition(check);
        check.addPoints(points * (factor - 1));
    }
}
