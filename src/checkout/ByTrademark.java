package checkout;

public class ByTrademark implements  Condition {
    private Trademark trademark;

    public ByTrademark(Trademark trademark) {
        this.trademark = trademark;
    }

    @Override
    public boolean inCondition(Check check) {
        return check.getCostByTrademark(trademark) > 0;
    }

    @Override
    public int getPointsByCondition(Check check) {
        return check.getCostByTrademark(trademark);
    }
}
