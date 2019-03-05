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
    private Product paper_4_by_Lasca;
    private Trademark lasca;
    private LocalDate nextDay;
    private LocalDate previousDay;

    @BeforeEach
    void setUp() {
        checkoutService = new CheckoutService();
        checkoutService.openCheck();

        lasca = new Trademark("Lasca");
        milk_7 = new Product(7, "Milk", Category.MILK);
        bred_3 = new Product(3, "Bred");
        paper_4_by_Lasca = new Product(4, "Paper", null, lasca);
        nextDay = LocalDate.now().plusDays(1);
        previousDay = LocalDate.now().minusDays(1);
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

        checkoutService.useOffer(new CombinedOffer(Condition.totalCostGreatThen(6), Reward.addPoints(2), nextDay));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(12));
    }

    @Test
    void useOffer__whenCostLessThanRequired__doNothing() {
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new CombinedOffer(Condition.totalCostGreatThen(6), Reward.addPoints(2), nextDay));
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
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, previousDay));
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, nextDay));
        checkoutService.addProduct(bred_3);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, previousDay));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffer__expiredDate__with__two__not__expired__offer() {
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, previousDay));
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, nextDay));
        checkoutService.addProduct(bred_3);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, nextDay));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(45));
    }

    @Test
    void useOffer__expiredDate__with__three__not__expired__offer() {
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, previousDay));
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, nextDay));
        checkoutService.addProduct(bred_3);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, nextDay));
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, nextDay));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(59));
    }

    @Test
    void combinedOffer__one__condition__and__reward() {
        checkoutService.useOffer(
                new CombinedOffer(Condition.totalCostGreatThen(15), Reward.addPoints(10), nextDay)
        );
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(17 + 10));
    }

    @Test
    void combinedOffer__two__condition__and__one__reward() {
        CombinedOffer combinedOffer = new CombinedOffer(Condition.totalCostGreatThen(15), Reward.addPoints(20), nextDay);
        combinedOffer.addCondition(Condition.categoryEqualsTo(Category.MILK));
        checkoutService.useOffer(combinedOffer);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(17 + 20));
    }

    @Test
    void combinedOffer__condition__by__outlet() {
        checkoutService.useOffer(new CombinedOffer(Condition.hasOutlet(lasca), Reward.discount(50), nextDay));
        checkoutService.addProduct(paper_4_by_Lasca);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(7));
    }

    @Test
    void combinedOffer__condition__by__product() {
        checkoutService.useOffer(new CombinedOffer(Condition.hasProduct(milk_7), Reward.factorPoints(2), nextDay));
        checkoutService.addProduct(paper_4_by_Lasca);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(14 * 2));
    }

    @Test
    void combinedOffer__two__reward() {
        CombinedOffer combinedOffer = new CombinedOffer(Condition.hasProduct(milk_7), Reward.factorPoints(2), nextDay);
        combinedOffer.addReward(Reward.addPoints(20));
        checkoutService.useOffer(combinedOffer);
        checkoutService.addProduct(paper_4_by_Lasca);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(14 * 2 + 20));
    }
}
