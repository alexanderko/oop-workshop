package checkout.offer_rewards;

import checkout.Check;
import checkout.offer_interfaces.Reward;

public class DiscountReward implements Reward {

    private int discount;

    public DiscountReward(int discount) {
        this.discount = discount;
    }

    @Override
    public void applyReward(Check check) {
        check.setDiscount(this.discount);
        check.getTotalCost();
    }

}
