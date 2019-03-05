import checkout.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CheckoutServiceTest {

    private Product milk_7;
    private CheckoutService checkoutService;
    private Product bred_3;
    private LocalDate standardExpirationDate;

    @BeforeEach
    void setUp() {
        LocalDate currentDate = LocalDate.of(2019, 3, 5);
        checkoutService = new CheckoutService(currentDate);
        checkoutService.openCheck();

        milk_7 = new Product(7, "Milk", Category.MILK);
        bred_3 = new Product(3, "Bred");
        standardExpirationDate = LocalDate.of(2100, 10, 1);
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

        checkoutService.useOffer(new AnyGoodsOffer(standardExpirationDate, 6, 2));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(12));
    }

    @Test
    void useOffer__whenCostLessThanRequired__doNothing() {
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new AnyGoodsOffer(standardExpirationDate, 6, 2));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(3));
    }

    @Test
    void useOffer__factorByCategory() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new FactorByCategoryOffer(standardExpirationDate, Category.MILK, 2));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffer__pastItsExpirationDate__doNothing() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        LocalDate expiredDate = LocalDate.of(2019, 2, 20);

        checkoutService.useOffer(new FactorByCategoryOffer(expiredDate, Category.MILK, 2));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(17));
    }

    @Test
    void useOffer__whenItsActive() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        LocalDate expiredDate = LocalDate.of(2019, 3, 20);

        checkoutService.useOffer(new FactorByCategoryOffer(expiredDate, Category.MILK, 2));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffer__whenItsExpirationEqualToCurrent() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        LocalDate equalExpirationDate = LocalDate.of(2019, 3, 5);

        checkoutService.useOffer(new FactorByCategoryOffer(equalExpirationDate, Category.MILK, 2));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void addProduct__whenOfferIsAlreadyUsed__applyIt() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        LocalDate expirationDate = LocalDate.of(2019, 3, 20);
        checkoutService.useOffer(new FactorByCategoryOffer(expirationDate, Category.MILK, 2));

        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);

        Check check = checkoutService.closeCheck();
        assertThat(check.getTotalPoints(), is(59));
    }

    @Test
    void useOffer__DiscountByCategory() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new DiscountByCategoryOffer(standardExpirationDate, 3));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(14));
    }
}
