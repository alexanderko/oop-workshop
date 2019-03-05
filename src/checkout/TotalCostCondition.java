package checkout;

public class TotalCostCondition implements Condition {

    private int totalCost;

    TotalCostCondition(int totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean isSatisfy(Check check) {
        return totalCost <= check.getTotalCost();
    }
}
