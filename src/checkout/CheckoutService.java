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

    public Check closeCheckAndUseOffer(){
        useOffer(new AnyGoodsOffer(6,2));
        useOffer(new FactorByCategoryOffer(Category.MILK, 2));
        useOffer(new SpecificBrandOffer(Brand.VOLOSHKOVE_POLE, 50));
        Check closedCheck = closeCheck();
        return  closedCheck;
    }

    public void useOffer(Offer offer) {
        offer.apply(check);
    }

    public void useOfferWithStrategy(OfferWithStrategy offer){
       offer.applyStrategy(check);
    }
}
