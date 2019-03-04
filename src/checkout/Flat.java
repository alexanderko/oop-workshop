package checkout;

public class Flat implements Reward {
    private int points;

    public Flat(int points) {
        this.points = points;
    }

    @Override
    public void useReward(Check check, Condition condition) {
        check.addPoints(points);
    }
}
