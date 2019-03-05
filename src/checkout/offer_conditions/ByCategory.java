package checkout.offer_conditions;

import checkout.Category;
import checkout.Check;
import checkout.offer_interfaces.Condition;

public class ByCategory implements Condition {

    private Category requiredCategory;
    private int requiredAmount;

    public ByCategory(Category requiredCategory, int requiredAmount) {
        this.requiredCategory = requiredCategory;
        this.requiredAmount = requiredAmount;
    }

    @Override
    public boolean checkCondition(Check check) {
        if (check.getCostByCategory(this.requiredCategory) > this.requiredAmount)
            return true;
        else
            return false;
    }
}
