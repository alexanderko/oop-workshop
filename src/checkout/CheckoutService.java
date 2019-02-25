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
        Check closedCheck = check;
        check = null;
        return closedCheck;
    }

    public void useOffer(Offer offer) {
        offer.apply(check);
        if (offer instanceof FactorByCategoryOffer) {
            FactorByCategoryOffer fbOffer = (FactorByCategoryOffer) offer;
            int points = check.getCostByCategory(fbOffer.category);
            check.addPoints(points * (fbOffer.factor - 1));
        } else {
            if (offer instanceof AnyGoodsOffer) {
                AnyGoodsOffer agOffer = (AnyGoodsOffer) offer;
                if (agOffer.totalCost <= check.getTotalCost())
                    check.addPoints(agOffer.points);
            }
        }
    }
}
