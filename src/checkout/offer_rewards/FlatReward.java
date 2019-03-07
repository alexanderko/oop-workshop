package checkout.offer_rewards;

import checkout.Check;
import checkout.offer_interfaces.Reward;

public class FlatReward implements Reward {

    private int aditionalPoints;

    public FlatReward(int points) {
        this.aditionalPoints = points;
    }

    @Override
    public void applyReward(Check check) {
        check.addPoints(this.aditionalPoints);
    }

}
