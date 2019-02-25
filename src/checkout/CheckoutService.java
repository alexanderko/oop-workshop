package checkout;

public class CheckoutService {

    private Check check = new Check();

    public void openCheck() {
        check = new Check();
    }

    public void addProduct(Product product) {
        check.addProduct(product);
    }

    public Check closeCheck() {
        Check oldCheck = check;
        openCheck();
        return oldCheck;
    }

}
