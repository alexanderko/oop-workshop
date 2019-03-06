package checkout;

import java.util.function.Predicate;

public class ConditionByCategory implements Condition {
    Category category;
    int totalCost;
    Predicate<Product> predicate = p -> p.category == category;

    public ConditionByCategory(Category category, int totalCost) {
        this.category = category;
        this.totalCost = totalCost;
    }

    public ConditionByCategory(Category category) {
        this.category = category;
        this.totalCost = 0;
    }

    @Override
    public boolean checkCondition(Check check) {
        return check.getSubCost(predicate) > totalCost;
    }

    @Override
    public int getCostForCondition(Check check) {
        return check.getSubCost(predicate);
    }
}
