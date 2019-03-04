package checkout;

public class Discount implements Reward {
    private int discont;

    public Discount(int discont) {
        this.discont = discont;
    }

    @Override
    public void useReward(Check check, Condition condition) {
        int points = condition.getPointsByCondition(check);
        check.addDiscount((int) Math.round(points * (discont / 100d)));
    }
}
