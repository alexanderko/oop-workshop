package checkout;

public class FlatReward implements Reward{
    public final int points;
    public FlatReward(int points){
        this.points = points;
    }

    @Override
    public void applyReward(Check check){
        if (points <= check.getTotalCost())
            check.addPoints(points);
    }
}
