package checkout;

public class AddTenPointsOffer extends Offer {
    @Override
    public void apply(Check check) {
        check.addPoints(10);
    }
}
