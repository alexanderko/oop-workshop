package checkout.offer_rewards;

import checkout.Check;
import checkout.offer_interfaces.Reward;

public class FlatReward implements Reward {

    @Override
    public void applyReward(Check check) {
        check.addPoints(check.getTotalCost());
    }

}
