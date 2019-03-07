package checkout.offer_rewards;

import checkout.Category;
import checkout.Check;
import checkout.offer_interfaces.Reward;

public class FactorByCategoryReward implements Reward {

    final Category category;
    final int factor;

    public FactorByCategoryReward(Category category, int factor) {
        this.category = category;
        this.factor = factor;
    }

    @Override
    public void applyReward(Check check) {
        int points = check.getCostByCategory(category);
        check.addPoints(points * (factor - 1));
    }

}
