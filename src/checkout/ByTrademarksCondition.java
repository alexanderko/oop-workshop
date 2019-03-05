package checkout;

public class ByTrademarksCondition implements Condition {

    private Trademark trademark;

    public ByTrademarksCondition(Trademark trademark) {
        this.trademark = trademark;
    }

    @Override
    public boolean isSatisfy(Check check) {
        return check.whetherHaveBrand(trademark);
    }
}
