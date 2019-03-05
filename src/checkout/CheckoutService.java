package checkout;

import checkout.offer_interfaces.Reward;

public class CheckoutService {

    private Check check;

    private Offer usedOffer;

    public void openCheck() {
        check = new Check();
    }

    public void addProduct(Product product) {
        if (check == null) {
            openCheck();
        }
        check.addProduct(product);
    }

    public Check closeCheck() {
        if (this.usedOffer != null) {
            this.usedOffer.applyOffer(check);
        }
        Check closedCheck = check;
        check = null;
        return closedCheck;
    }

    public void useOffer(Offer offer) {
        this.usedOffer = offer;
    }


}
