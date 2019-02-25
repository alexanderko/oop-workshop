package checkout;

public class FactorByCategoryOffer extends AnyGoodsOffer {
    final Category category;
    final int factor;

    public FactorByCategoryOffer(Category category, int factor) {
        super(0, 0);
        this.category = category;
        this.factor = factor;
    }
}
