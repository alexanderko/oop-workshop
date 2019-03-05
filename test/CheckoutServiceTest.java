import checkout.*;
import checkout.offer_conditions.ByCategory;
import checkout.offer_conditions.ByTotalCost;
import checkout.offer_rewards.AnyGoodsReward;
import checkout.offer_rewards.DiscountReward;
import checkout.offer_rewards.FactorByCategoryReward;
import checkout.offer_rewards.FlatReward;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CheckoutServiceTest {

    private Product milk_7;
    private CheckoutService checkoutService;
    private Product bred_3;
    private Offer specialOffer;

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

        specialOffer = new Offer(new AnyGoodsReward(6, 2));

        checkoutService.useOffer(specialOffer);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(12));
    }

    @Test
    void useOffer__whenCostLessThanRequired__doNothing() {
        checkoutService.addProduct(bred_3);

        specialOffer = new Offer(new AnyGoodsReward(6, 2));

        checkoutService.useOffer(specialOffer);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(3));
    }



    @Test
    void userOffer__useOfferWhileBuying__FlatReward() {
        checkoutService.addProduct(milk_7);
        specialOffer = new Offer(new FlatReward(10), new ByTotalCost(15));
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        checkoutService.useOffer(specialOffer);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(27));
    }

    @Test
    void userOffer__FlatOfferReward__ByTotalCost() {
        checkoutService.addProduct(milk_7);

        specialOffer = new Offer(new FlatReward(10), new ByTotalCost(5));
        checkoutService.useOffer(specialOffer);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(17));
    }

    @Test
    void userOffer__FlatOfferReward__ByCategory() {
        checkoutService.addProduct(milk_7);

        specialOffer = new Offer(new FlatReward(10), new ByCategory(Category.MILK));
        checkoutService.useOffer(specialOffer);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(17));
    }

    @Test
    void userOffer__FactorByCategoryReward__ByCategory() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        specialOffer = new Offer(new FactorByCategoryReward(Category.MILK, 2), new ByCategory(Category.MILK, 10));
        checkoutService.useOffer(specialOffer);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void userOffer__FactorByCategoryReward__ByTotalAmount() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        specialOffer = new Offer(new FactorByCategoryReward(Category.MILK, 2), new ByTotalCost(10));
        checkoutService.useOffer(specialOffer);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void userOffer__DiscountReward__ByCategory() {
        Product beef_100 = new Product(100, "beef", Category.MEET);
        checkoutService.addProduct(beef_100);

        specialOffer = new Offer(new DiscountReward(20), new ByCategory(Category.MEET));
        checkoutService.useOffer(specialOffer);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(80));
    }

    @Test
    void userOffer__checkExpirationDate__Expired() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        specialOffer = new Offer(new FactorByCategoryReward(Category.MILK, 2),
                                 new ByTotalCost(10),
                                 LocalDate.of(2000, Month.AUGUST, 15));

        checkoutService.useOffer(specialOffer);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(17));
    }

    @Test
    void userOffer__checkExpirationDate__NotExpired() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        specialOffer = new Offer(new FactorByCategoryReward(Category.MILK, 2),
                new ByTotalCost(10),
                LocalDate.of(2020, Month.AUGUST, 15));

        specialOffer.setDateOfCheck(LocalDate.of(1995, Month.MARCH, 5));

        checkoutService.useOffer(specialOffer);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

}