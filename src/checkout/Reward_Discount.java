package checkout;

public class Reward_Discount implements Reward {

    private int discount;

    public Reward_Discount(int discount) {
        this.discount = discount;
    }

    @Override
    public void applyReward(Check check) {
        check.addDiscount(discount);
    }
}
