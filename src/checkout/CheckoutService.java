package checkout;

public class CheckoutService {

    private Check check;

    public void openCheck() {
        check = new Check();
    }

    public void addProduct(Product product) {
        if (isClosed()) {
            openCheck();
        }
        check.addProduct(product);
    }

    public Check closeCheck() {
        final Check result = check;
        check = null;
        return result;
    }

    private boolean isClosed() {
        return check == null;
    }
}
