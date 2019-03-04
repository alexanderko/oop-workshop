package checkout;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {

    private Check check;
    private List<Offer> offers = new ArrayList<>();

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
        Check closedCheck = check;
        for (Offer offer: offers) {
            offer.apply(check);
        }
        check = null;
        return closedCheck;
    }

    public void useOffer(Offer offer) {
        offers.add(offer);
    }
}
