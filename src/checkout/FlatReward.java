package checkout;

public class FlatReward implements Reward{
    int flat;
    public FlatReward(int flat) {
        this.flat = flat;
    }

    public void applyReward(Check check, int cost){
        check.addPoints(flat);
    }
}

