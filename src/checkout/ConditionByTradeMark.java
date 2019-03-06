package checkout;

import java.util.function.Predicate;

public class ConditionByTradeMark implements Condition {
    TradeMark tradeMark;
    int totalCost;
    Predicate<Product> productPredicate = p -> p.tradeMark == tradeMark;

    public ConditionByTradeMark(TradeMark tradeMark, int totalCost) {
        this.tradeMark = tradeMark;
        this.totalCost = totalCost;
    }

    public ConditionByTradeMark(TradeMark tradeMark) {
        this.tradeMark = tradeMark;
        this.totalCost = 0;
    }

    @Override
    public boolean checkCondition(Check check) {
        return check.getSubCost(productPredicate) > totalCost;
    }

    @Override
    public int getCostForCondition(Check check) {
        return check.getSubCost(productPredicate);
    }
}
