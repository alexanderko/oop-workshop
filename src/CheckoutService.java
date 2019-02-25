import java.util.ArrayList;

public class CheckoutService {

    private Check check;

    public void openCheck() {
        check = new Check();
        check.products = new ArrayList<>();
        check.totalCost = 0;
    }

    public void addProduct(Product product) {
        check.products.add(product);
    }


    public Check closeCheck() {
        for (Product product : check.products) {
            check.totalCost += product.price;
        }
        return check;
    }
}
