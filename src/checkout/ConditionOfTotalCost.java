package checkout;

public class ConditionOfTotalCost implements Condition {
    int totalCost;
    public ConditionOfTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public boolean checkCondition(Check check) {
        if (check.getTotalCost() < totalCost) {
            return false;
        }else{
            return true;
        }
    }
    public int getCostForCondition(Check check) {
        return check.getTotalPrice();
    }
}
