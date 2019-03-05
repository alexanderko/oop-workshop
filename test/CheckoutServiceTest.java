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
    void useOffer__addTenPoints() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new AddTenPointsOffer());
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(check.getTotalCost() + 10));
    }

    @Test
    void useOffer__add__offer__before__add__all__product() {
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2));
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffer__expiredDate__with__one__not__expired__offer() {
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, LocalDate.of(2000, 1, 1)));
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, LocalDate.of(2020, 1, 1)));
        checkoutService.addProduct(bred_3);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, LocalDate.of(2000, 1, 1)));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffer__expiredDate__with__two__not__expired__offer() {
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, LocalDate.of(2000, 1, 1)));
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, LocalDate.of(2020, 1, 1)));
        checkoutService.addProduct(bred_3);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, LocalDate.of(2020, 1, 1)));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(45));
    }

    @Test
    void useOffer__expiredDate__with__three__not__expired__offer() {
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, LocalDate.of(2000, 1, 1)));
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, LocalDate.of(2020, 1, 1)));
        checkoutService.addProduct(bred_3);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, LocalDate.of(2020, 1, 1)));
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, LocalDate.of(2020, 1, 1)));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(59));
    }
}
