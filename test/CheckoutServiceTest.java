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
    private LocalDate currentDate;

    private Offer anyGoodsOffer;
    private Offer factorByCategoryOffer;

    @BeforeEach
    void setUp() {
        currentDate = LocalDate.now();
        standardExpirationDate = currentDate.plusDays(1);
        checkoutService = new CheckoutService(currentDate);
        checkoutService.openCheck();

        anyGoodsOffer = new OfferBuilder()
                .setExpiration(standardExpirationDate)
                .flatReward(6, 2)
                .costByTotalCost(6)
                .build();

        factorByCategoryOffer = new OfferBuilder()
                .setExpiration(standardExpirationDate)
                .factorReward(Category.MILK, 2)
                .costByCategory(Category.MILK)
                .build();

        milk_7 = new Product(7, "Milk", Category.MILK);
        bred_3 = new Product(3, "Bred", Category.BRED);
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

        checkoutService.useOffer(anyGoodsOffer);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(12));
    }

    @Test
    void useOffer__whenCostLessThanRequired__doNothing() {
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(anyGoodsOffer);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(3));
    }

    @Test
    void useOffer__factorByCategory() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(factorByCategoryOffer);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffer__pastItsExpirationDate__doNothing() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Offer offer = new OfferBuilder()
                .setExpiration(currentDate.minusDays(1))
                .costByCategory(Category.MILK)
                .factorReward(Category.MILK, 2)
                .build();

        checkoutService.useOffer(offer);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(17));
    }

    @Test
    void useOffer__whenItsActive() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(factorByCategoryOffer);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffer__whenItsExpirationEqualToCurrent() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Offer offer = new OfferBuilder()
                .setExpiration(currentDate)
                .costByCategory(Category.MILK)
                .factorReward(Category.MILK, 2)
                .build();

        checkoutService.useOffer(offer);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void addProduct__whenOfferIsAlreadyUsed__applyIt() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        checkoutService.useOffer(factorByCategoryOffer);

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

        Offer discountByCategoryOffer = new OfferBuilder()
                .setExpiration(standardExpirationDate)
                .discountReward(3)
                .costByCategory(Category.BRED)
                .build();

        checkoutService.useOffer(discountByCategoryOffer);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(14));
    }

    @Test
    void useOffer__DiscountByBrand() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(new Product(10, "SomeMilk", Category.MILK, Trademark.GREEN_VALLEY));
        checkoutService.addProduct(new Product(10, "LuxuryBred", Category.BRED, Trademark.CHERKASY_BRED));
        checkoutService.addProduct(bred_3);

        Offer discountByCategoryOffer = new OfferBuilder()
                .setExpiration(standardExpirationDate)
                .discountReward(2)
                .costByTrademark(Trademark.CHERKASY_BRED)
                .build();

        checkoutService.useOffer(discountByCategoryOffer);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(28));
    }
}
