package checkout;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {

    private Check check;
    private List<Offer> offers = new ArrayList<>();

    public void openCheck() {
        offers = new ArrayList<>();
        check = new Check();
    }

    public void addProduct(Product product) {
        if (check == null) {
            openCheck();
        }
        check.addProduct(product);
    }

    public Check closeCheck() {
        useAllOffer();
        Check closedCheck = check;
        check = null;
        return closedCheck;
    }

    private void useAllOffer() {
        if(this.offers.size()!=0) this.offers.forEach(offer ->
            offer.apply(check));
    }

    public void useOffer(Offer offer) {
        if(check != null)
            this.offers.add(offer);
    }
}
