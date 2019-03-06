package checkout;

public class CheckoutService {

    private Check check;

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
        check.useOffers(check);
        Check closedCheck = check;
        check = null;
        return closedCheck;
    }

    public void useOffer(Offer offer) {
        check.addOffer(offer);
    }
}
