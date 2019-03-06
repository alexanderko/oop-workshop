package checkout;

import com.sun.javaws.exceptions.OfflineLaunchException;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {

    private Check check;

    private Offer usedOffer;
    private ArrayList<Offer> currentOffers = new ArrayList<>();

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
        if (currentOffers.size() >= 1) {
            this.currentOffers.forEach(item -> {
                if (item.isOfferValid(check))
                    item.applyOffer(check);
            });
        }
//        if (this.usedOffer != null && this.usedOffer.isOfferValid(check)) {
//            this.usedOffer.applyOffer(check);
//        }
        Check closedCheck = check;
        check = null;
        return closedCheck;
    }

    public void useOffer(Offer offer) {
//        this.usedOffer = offer;
        this.currentOffers.add(offer);
    }


}
