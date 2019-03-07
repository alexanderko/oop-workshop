package checkout;

public class TotalCost implements Condition {
    int totalCost;

    public TotalCost(int totalCost){
        setTotalCost(totalCost);
    }

    @Override
    public boolean checkCondition(Check check) {
        return (totalCost <= check.getTotalCost());
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
