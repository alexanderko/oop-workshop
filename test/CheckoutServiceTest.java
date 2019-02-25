import checkout.Check;
import checkout.CheckoutService;
import checkout.Product;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CheckoutServiceTest {

    @Test
    void closeCheck__withOneProduct() {
        CheckoutService checkoutService = new CheckoutService();
        checkoutService.openCheck();

        checkoutService.addProduct(new Product(7, "Milk"));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(7));
    }

    @Test
    void closeCheck__withTwoProducts() {
        CheckoutService checkoutService = new CheckoutService();
        checkoutService.openCheck();

        checkoutService.addProduct(new Product(7, "Milk"));
        checkoutService.addProduct(new Product(3, "Bred"));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(10));
    }

    @Test
    void addProduct__whenCheckIsClosed__opensNewCheck() {
        CheckoutService checkoutService = new CheckoutService();

        checkoutService.addProduct(new Product(7, "Milk"));
        Check milkCheck = checkoutService.closeCheck();
        assertThat(milkCheck.getTotalCost(), is(7));

        checkoutService.addProduct(new Product(3, "Bred"));
        Check bredCheck = checkoutService.closeCheck();
        assertThat(bredCheck.getTotalCost(), is(3));
    }
}
