import checkout.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CheckoutServiceTest {

    private Product milk_7;
    private CheckoutService checkoutService;
    private Product bred_3;

    @BeforeEach
    void setUp() {
        checkoutService = new CheckoutService();
        checkoutService.openCheck();

        milk_7 = new Product(7, "Milk", Category.MILK);
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
    void useOffer__addOfferPoints() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new AnyGoodsOffer(6, 2));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(12));
    }

    @Test
    void useOffer__whenCostLessThanRequired__doNothing() {
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new AnyGoodsOffer(6, 2));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(3));
    }

    @Test
    void useOffer__factorByCategory() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }


    @Test
    void useOffer__checkDateExpiration__FactorByCategoryOffer() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        FactorByCategoryOffer spOffer = new FactorByCategoryOffer(Category.MILK, 2);
        spOffer.setExpireDate(1992, Month.JUNE, 23);

        checkoutService.useOffer(spOffer);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(17));
    }

    @Test
    void useOffer__checkDateExpiration__AnyGoodsOffer() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        AnyGoodsOffer spOffer = new AnyGoodsOffer(6, 2);
        spOffer.setExpireDate(1950, Month.JANUARY, 10);

        checkoutService.useOffer(spOffer);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(10));
    }


    @Test
    void useOffer__HalfPriceOffer() {
        Product apple_10 = new Product(10, "Apple", Category.FRUIT);
        Product cabage_4 = new Product(4, "Cabage", Category.VEGETABLE);
        Product veal_15 = new Product(15, "Veal", Category.MEET);


        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(apple_10);
        checkoutService.addProduct(cabage_4);
        checkoutService.addProduct(veal_15);

        checkoutService.useOffer(new HalfPriceOffer(Category.FRUIT));

        Check check = checkoutService.closeCheck();
        assertThat(check.getTotalCost(), is(31));
    }
}
