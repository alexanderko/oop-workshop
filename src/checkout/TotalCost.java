package checkout;

public class TotalCost  implements Condition {
    @Override
    public boolean checkCondition(Check check) {
        return (10 <= check.getTotalCost());
    }
}