package checkout;

public class CheckoutService {

    private Check check;

    public void openCheck() {
        check = new Check();
    }

    public void addProduct(Product product) {
        check.addProduct(product);
    }

    public Check closeCheck() {
        return check;
    }
}
