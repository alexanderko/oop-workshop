import checkout.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CheckoutServiceTest {

    private CheckoutService checkoutService;

    private Product milk_7;
    private Product bred_3;
    private Product paper_4_by_Lasca;
    private Product water_6;

    private Trademark lasca;

    private LocalDate nextDay;
    private LocalDate previousDay;

    private Offer factorPointsByCategoryExpired;
    private Offer factorPointsByCategoryNotExpired;
    private Offer factorPointsByTrademark;


    @BeforeEach
    void setUp() {
        checkoutService = new CheckoutService();
        checkoutService.openCheck();

        lasca = new Trademark("Lasca");
        milk_7 = new Product(7, "Milk", Category.MILK);
        bred_3 = new Product(3, "Bred");
        water_6 = new Product(6, "Water", Category.WATER);
        paper_4_by_Lasca = new Product(4, "Paper", null, lasca);
        nextDay = LocalDate.now().plusDays(1);
        previousDay = LocalDate.now().minusDays(1);
        factorPointsByCategoryExpired = new Offer(Rewards.factorPointsByCategory(2, Category.MILK), previousDay);
        factorPointsByCategoryNotExpired = new Offer(Rewards.factorPointsByCategory(2, Category.MILK), nextDay);
        factorPointsByTrademark = new Offer(Rewards.factorPointsByTrademark(2, lasca), nextDay);
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

        checkoutService.useOffer(new Offer(Conditions.totalCostGreatThen(6), Rewards.addPoints(2), nextDay));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(12));
    }

    @Test
    void useOffer__whenCostLessThanRequired__doNothing() {
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new Offer(Conditions.totalCostGreatThen(6), Rewards.addPoints(2), nextDay));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(3));
    }

    @Test
    void useOffer__factorByCategory() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(factorPointsByCategoryNotExpired);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffer__whenAddOfferBeforeAllProduct__offerMustApplyForAllProduct() {
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(factorPointsByCategoryNotExpired);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffers__whenOneFromThreeOffersNotExpired__applyOnlyNotExpiredOffers() {
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(factorPointsByCategoryExpired);
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(factorPointsByCategoryNotExpired);
        checkoutService.addProduct(bred_3);
        checkoutService.useOffer(factorPointsByCategoryExpired);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffers__whenTwoFromThreeOffersNotExpired__applyOnlyNotExpiredOffers() {
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(factorPointsByCategoryExpired);
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(factorPointsByCategoryNotExpired);
        checkoutService.addProduct(bred_3);
        checkoutService.useOffer(factorPointsByCategoryNotExpired);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(45));
    }

    @Test
    void totalCostGreatThenAndAddPoints__applyOffer() {
        checkoutService.useOffer(
                new Offer(Conditions.totalCostGreatThen(15), Rewards.addPoints(10), nextDay)
        );
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(17 + 10));
    }

    @Test
    void ConditionsAndRewards__whenAddOneCondition__applyAdditionalOffer() {
        Offer offer = new Offer(Conditions.totalCostGreatThen(15), Rewards.addPoints(20), nextDay);
        offer.addCondition(Conditions.categoryEqualsTo(Category.MILK));
        checkoutService.useOffer(offer);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(17 + 20));
    }

    @Test
    void hasTrademarkAndDiscount__applyOffer() {
        checkoutService.useOffer(new Offer(Conditions.hasTrademark(lasca), Rewards.discountForTrademark(50, lasca), nextDay));
        checkoutService.addProduct(paper_4_by_Lasca);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(7 + 3 + 4 / 2));
    }

    @Test
    void hasProductAndFactorPoints__applyOffer() {
        checkoutService.useOffer(new Offer(Conditions.hasProduct(milk_7), Rewards.factorPoints(2), nextDay));
        checkoutService.addProduct(paper_4_by_Lasca);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(14 * 2));
    }

    @Test
    void addReward__applyAddictionReward() {
        Offer offer = new Offer(Conditions.hasProduct(milk_7), Rewards.factorPoints(2), nextDay);
        offer.addReward(Rewards.addPoints(20));
        checkoutService.useOffer(offer);
        checkoutService.addProduct(paper_4_by_Lasca);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(14 * 2 + 20));
    }

    @Test
    void factorPointsByTrademark__applyReward() {
        checkoutService.useOffer(factorPointsByTrademark);
        checkoutService.addProduct(paper_4_by_Lasca);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(7 + 3 + 4 * 2));
    }

    @Test
    void discountByCategory__applyReward() {
        checkoutService.useOffer(new Offer(Conditions.hasTrademark(lasca), Rewards.discountByCategory(50, Category.WATER), nextDay));
        checkoutService.addProduct(paper_4_by_Lasca);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(water_6);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(4 + 7 + 6 / 2));

    }
}
