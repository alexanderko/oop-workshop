import checkout.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CheckoutServiceTest {

    private Product milk_7;
    private CheckoutService checkoutService;
    private Product bred_3;
    private Product meat_10;

    @BeforeEach
    void setUp() {
        checkoutService = new CheckoutService();
        checkoutService.openCheck();

        milk_7 = new Product(7, "Milk", Category.MILK, Trademark.COLA);
        meat_10 = new Product(10, "Meat", Category.MEAT);
        bred_3 = new Product(3, "Bred");
    }

    @Test
    void closeCheck__withOneProduct() {
        checkoutService.addProduct(milk_7);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(7));
    }

    @Test
    void closeCheck__withTwoProducts() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(10));
    }

    @Test
    void addProduct__whenCheckIsClosed__opensNewCheck() {
        checkoutService.addProduct(milk_7);
        Check milkCheck = checkoutService.closeCheck();
        assertThat(milkCheck.getTotalCost(), is(7));

        checkoutService.addProduct(bred_3);
        Check bredCheck = checkoutService.closeCheck();
        assertThat(bredCheck.getTotalCost(), is(3));
    }

    @Test
    void closeCheck__calcTotalPoints() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(10));
    }


    @Test
    void useSuperOffer_TotalCost_Flat() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new SuperOffer(new TotalCost(10), new Flat(10), new Date(2019, 5, 10)));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(27));
    }

    void useSuperOffer_ByCategory_Discount() {
        checkoutService.useOffer(new SuperOffer(new ByCategory(Category.MILK), new Discount(60), new Date(2019, 5, 10)));
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getDiscount(), is(8));
    }

    void useSuperOffer_ByTrademark_Factor() {
        checkoutService.useOffer(new SuperOffer(new ByTrademark(Trademark.COLA), new Factor(2), new Date(2019, 5, 10)));
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    void useSuperOffer_ByTrademark_Factor_InvalidTrademark() {
        checkoutService.useOffer(new SuperOffer(new ByTrademark(Trademark.FANTA), new Factor(2), new Date(2019, 5, 10)));
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(17));
    }

    void useSuperOffer_AfterExtensionTime() {
        checkoutService.useOffer(new SuperOffer(new ByTrademark(Trademark.COLA), new Factor(2), new Date(2019, 1, 10)));
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(17));
    }

    void useSuperOffer_ByCategory_Discount_InvalidCategory() {
        checkoutService.useOffer(new SuperOffer(new ByCategory(Category.MEAT), new Discount(60), new Date(2019, 5, 10)));
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getDiscount(), is(0));
    }
}
