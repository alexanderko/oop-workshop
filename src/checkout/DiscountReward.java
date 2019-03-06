package checkout;

public class DiscountReward implements Reward {
    int discount;

    public DiscountReward(int discount) {
        this.discount = discount;
    }

    public void applyReward(Check check, int cost) {
        check.addDiscount(cost * discount / 100.0);
    }
}
