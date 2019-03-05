package checkout;

public class TotalCostCondition implements Condition {
    public final int factor;
    public TotalCostCondition(int factor){
        this.factor = factor;
    }

    public boolean applyCondition(Check check){
        return check.getTotalCost() == factor;
    }
}
