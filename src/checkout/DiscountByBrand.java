package checkout;

public class DiscountByBrand implements Reward {
    private double discount;
    private Brand brand;

    public DiscountByBrand(double discount, Brand brand) {
        setBrand(brand);
        setDiscount(discount);
    }

    @Override
    public void apply(Check check) {
        Double points = check.getPointsForBrand(brand) * discount * 10;
        check.addDiscount(points.intValue());
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
