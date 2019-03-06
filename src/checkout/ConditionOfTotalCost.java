package checkout;

public class ConditionOfTotalCost implements Condition {
    int totalCost;

    public ConditionOfTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public boolean checkCondition(Check check) {
        return totalCost <= check.getTotalCost();
    }

    public int getCostForCondition(Check check) {
        return check.getTotalPrice();
    }
}
