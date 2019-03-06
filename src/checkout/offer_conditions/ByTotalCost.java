package checkout.offer_conditions;

import checkout.Check;
import checkout.offer_interfaces.Condition;

public class ByTotalCost implements Condition {

    private int requiredAmount;

    public ByTotalCost(int requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    @Override
    public boolean checkCondition(Check check) {
        return requiredAmount < check.getTotalCost();
    }

}

