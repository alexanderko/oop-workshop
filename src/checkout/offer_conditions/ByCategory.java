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

    public ByCategory(Category requiredCategory) {
        this.requiredCategory = requiredCategory;
        this.requiredAmount = 0;
    }

    @Override
    public boolean checkCondition(Check check) {
        return (this.requiredAmount < check.getCostByCategory(this.requiredCategory));
    }
}
