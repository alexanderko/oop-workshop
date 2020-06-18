package checkout;

public class TotalCost implements  Condition {
    private int amount;

    public TotalCost(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean inCondition(Check check) {
        return check.getTotalCost() >= amount;
    }

    @Override
    public int getPointsByCondition(Check check) {
        return check.getTotalCost();
    }
}
