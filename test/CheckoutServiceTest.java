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
    private Product milk_WithBrand;

    @BeforeEach
    void setUp() {
        checkoutService = new CheckoutService();
        checkoutService.openCheck();

        milk_7 = new Product(7, "Milk", Category.MILK);
        bred_3 = new Product(3, "Bred");
        milk_WithBrand = new Product(7, "Milk", Category.MILK, Brand.VOLOSHKOVE_POLE);
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
    void useOffer_whenCheckClose(){
        checkoutService.useOffer(new Offer(new FlatReward(20), new TotalCost(10)));
        checkoutService.useOffer(new Offer(new FactorRewardByCategory(2, Category.MILK), new ByCategory(Category.MILK)));
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct((bred_3));
        checkoutService.addProduct(milk_7);
        Check check = checkoutService.closeCheck();
        assertThat(check.getTotalPoints(), is(51));
    }


    @Test
    void isOfferAvaliable(){
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct((bred_3));
        Offer offer = new Offer(new FlatReward(20), new TotalCost(10), LocalDate.of(2018, 2, 3));
        checkoutService.useOffer(offer);
        Check check = checkoutService.closeCheck();
        assertThat(check.getTotalPoints(), is(17));
    }

    @Test
    void useOffer_Strategy_TotalCost(){
        checkoutService.addProduct(milk_WithBrand);
        checkoutService.addProduct(milk_WithBrand);
        Offer offer = new Offer(new FlatReward(20), new TotalCost(10));
        checkoutService.useOffer(offer);
        Check check = checkoutService.closeCheck();
        assertThat(check.getTotalPoints(), is(34));
    }
    @Test
    void useOffer_Strategy_TotalCost_Whan_Cost_Less_10(){
        checkoutService.addProduct(bred_3);
        checkoutService.addProduct(bred_3);
        checkoutService.addProduct(bred_3);
        Offer offer = new Offer(new FlatReward(20), new TotalCost(10));
        checkoutService.useOffer(offer);
        Check check = checkoutService.closeCheck();
        assertThat(check.getTotalPoints(), is(9));
    }

    @Test
    void useOffer_Strategy_ByCategory(){
        checkoutService.addProduct(milk_WithBrand);
        checkoutService.addProduct(milk_WithBrand);
        Offer offer = new Offer(new FactorRewardByCategory(2, Category.MILK), new ByCategory(Category.MILK));
        checkoutService.useOffer(offer);
        Check check = checkoutService.closeCheck();
        assertThat(check.getTotalPoints(), is(28));
    }

    @Test
    void useOffer_Strategy_ByCategory_Whan_Category_Else(){
        checkoutService.addProduct(bred_3);
        checkoutService.addProduct(bred_3);
        Offer offer = new Offer(new FactorRewardByCategory(2, Category.MILK), new ByCategory(Category.MILK));
        checkoutService.useOffer(offer);
        Check check = checkoutService.closeCheck();
        assertThat(check.getTotalPoints(), is(6));
    }

    @Test
    void useOffer_Strategy_ByBrand(){
        checkoutService.addProduct(milk_WithBrand);
        checkoutService.addProduct(milk_WithBrand);
        Offer offer = new Offer(new DiscountByBrand(0.5, Brand.VOLOSHKOVE_POLE), new ByBrand(Brand.VOLOSHKOVE_POLE));
        checkoutService.useOffer(offer);
        Check check = checkoutService.closeCheck();
        assertThat(check.getTotalCostWithDiscount(), is(7));
    }

    @Test
    void useOffer_Strategy_When_No_Brand(){
        checkoutService.addProduct(bred_3);
        checkoutService.addProduct(milk_7);
        Offer offer = new Offer(new DiscountByBrand(0.5, Brand.VOLOSHKOVE_POLE), new ByBrand(Brand.VOLOSHKOVE_POLE));
        checkoutService.useOffer(offer);
        Check check = checkoutService.closeCheck();
        assertThat(check.getTotalCostWithDiscount(), is(10));

    }
}
