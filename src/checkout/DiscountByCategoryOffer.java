package checkout;


import java.time.LocalDate;

public class DiscountByCategoryOffer extends Offer {
    Category category;
    int discount;
    public DiscountByCategoryOffer(Category category, int discount, LocalDate expiredDate) {
        super(expiredDate);
        this.category = category;
        this.discount = discount;
    }

    @Override
    public void applyAfterCheck(Check check) {
        double cost = check.getCostByCategory(category);
        check.addDiscount(cost*discount/100);
    }
}
