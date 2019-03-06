package checkout;

public class FlatReward implements Reward {
    private int reward;

    public FlatReward (int reward){
        setReward(reward);
    }
    @Override
    public void apply(Check check) {
        check.addPoints(reward);
    }

    public void setReward(int reward) {
        this.reward = reward;
    }
}