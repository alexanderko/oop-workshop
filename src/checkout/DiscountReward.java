package checkout;

public class DiscountReward implements Reward {

    private int discount;

    public DiscountReward(int discount) {
        this.discount = discount;
    }

    @Override
    public void applyReward(Check check) {
        check.addDiscount(discount);
    }
}
