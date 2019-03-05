import checkout.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.security.x509.CertAttrSet;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CheckoutServiceTest {

    private Product milk_7;
    private CheckoutService checkoutService;
    private Product bred_3;
    private LocalDate expiredDate = LocalDate.of(2100, 4, 14);

    @BeforeEach
    void setUp() {
        checkoutService = new CheckoutService();
        checkoutService.openCheck();

        milk_7 = new Product(7, "Milk", Category.MILK, Outlet.PEPSI);
        bred_3 = new Product(3, "Bred", Category.BRED, Outlet.ROSHEN);
    }

    @Test
    void useFactorReward_whenCostMoreThenRequired() {
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new Offer(expiredDate, new FactorReward(3), new ConditionOfTotalCost(10)));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(7));
    }

    @Test
    void useFactorRewardAndConditionByCategory() {
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new Offer(expiredDate, new FactorReward(3), new ConditionByCategory(Category.MILK)));
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(45));
    }

    @Test
    void useFlatRewardAndConditionByCategory() {
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new Offer(expiredDate, new FlatReward(100), new ConditionByCategory(Category.MILK)));
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(117));
    }
    @Test
    void useFlatRewardAndConditionByOutlet() {
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new Offer(expiredDate, new FlatReward(30), new ConditionByOutlet(Outlet.ROSHEN)));
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(47));
    }
    @Test
    void useDiscountRewardAndConditionByOutlet() {
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new Offer(expiredDate, new DiscountReward(50), new ConditionByOutlet(Outlet.ROSHEN)));
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(15.5));
    }

    @Test
    void useDiscountRewardAndConditionByOutlet__whenOutletNotFound() {
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new Offer(expiredDate, new DiscountReward(50), new ConditionByOutlet(Outlet.LAYS)));
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(17.0));
    }

    @Test
    void useDiscountRewardAndConditionByTotalCost() {
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new Offer(expiredDate, new DiscountReward(50), new ConditionOfTotalCost(7)));
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(8.5));
    }

    @Test
    void useDiscountRewardAndConditionByTotalCost__whenTotalCostLessThenRequire() {
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new Offer(expiredDate, new DiscountReward(50), new ConditionOfTotalCost(50)));
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(17.0));
    }
}

