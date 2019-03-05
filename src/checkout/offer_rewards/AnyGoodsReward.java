package checkout.offer_rewards;

import checkout.Check;
import checkout.offer_interfaces.Reward;

public class AnyGoodsReward implements Reward {

    public final int totalCost;
    public final int points;

    public AnyGoodsReward(int totalCost, int points) {
        this.totalCost = totalCost;
        this.points = points;
    }

    @Override
    public void applyReward(Check check) {
        if (totalCost <= check.getTotalCost())
            check.addPoints(points);
    }
}
