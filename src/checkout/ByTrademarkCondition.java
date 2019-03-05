package checkout;

public class ByTrademarkCondition implements Condition {
    final Trademark trademark;
    final float count;

    public ByTrademarkCondition(Trademark trademark, float count){
        this.trademark = trademark;
        this.count = count;
    }
    public boolean applyCondition(Check check){
        return check.getCountProductsByTrademark(trademark) == count;
    }
}
