package checkout;

public class Condition_TotalCost implements Condition {

    private int totalCost;

    public Condition_TotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean isSatisfiedConditionals(Check check) {
        return totalCost <= check.getTotalCost();
    }
}
