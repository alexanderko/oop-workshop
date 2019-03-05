import checkout.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void useOffer_beforeCheckClose(){
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct((bred_3));
        checkoutService.useOffer(new AnyGoodsOffer(6,2));
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2));
        Check check = checkoutService.closeCheck();
        assertThat(check.getTotalPoints(), is(33));
        assertThat(check.getTotalCost(), is(17));
        assertThat(check.getTotalCostWithDiscount(), is(17));
    }

    @Test
    void useOffer_whenCheckClose(){
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct((bred_3));
        Check check = checkoutService.closeCheckAndUseOffer();
        assertThat(check.getTotalCostWithDiscount(), is(17));
    }

    @Test
    void isFactorByCategoryOfferAilable(){
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct((bred_3));
        Offer offer = new FactorByCategoryOffer(Category.MILK, 2);
        assertThat(offer.isOfferavAilable(), is(false));
    }

    @Test
    void isAnyGoodsOfferAvaliable(){
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct((bred_3));
        Offer offer = new AnyGoodsOffer(6,2);
        assertThat(offer.isOfferavAilable(), is(true));
    }

    @Test
    void useOffer_SpecificBrandOffer(){
        checkoutService.addProduct(milk_WithBrand);
        checkoutService.addProduct(milk_WithBrand);
        checkoutService.useOffer(new SpecificBrandOffer(Brand.VOLOSHKOVE_POLE, 50));
        Check check = checkoutService.closeCheck();
        assertThat(check.getTotalCostWithDiscount(), is(7));
    }

    @Test
    void useOffer_Strategy_TotalCost(){
        checkoutService.addProduct(milk_WithBrand);
        checkoutService.addProduct(milk_WithBrand);
        OfferWithStrategy offer = new OfferWithStrategy(new Flat(), new TotalCost());
        checkoutService.useOfferWithStrategy(offer);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(34));
    }

    @Test
    void useOffer_Strategy_ByCategory(){
        checkoutService.addProduct(milk_WithBrand);
        checkoutService.addProduct(milk_WithBrand);
        OfferWithStrategy offer = new OfferWithStrategy(new Factor(), new ByCategory());
        checkoutService.useOfferWithStrategy(offer);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(28));
    }

    @Test
    void useOffer_Strategy_ByBrand(){
        checkoutService.addProduct(milk_WithBrand);
        checkoutService.addProduct(milk_WithBrand);
        OfferWithStrategy offer = new OfferWithStrategy(new Discount(), new ByBrand());
        checkoutService.useOfferWithStrategy(offer);
        Check check = checkoutService.closeCheck();


        assertThat(check.getTotalCostWithDiscount(), is(7));
    }
}
