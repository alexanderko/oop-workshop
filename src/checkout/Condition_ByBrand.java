package checkout;

public class Condition_ByBrand implements Condition {

    private Brand brand;

    public Condition_ByBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public boolean isSatisfiedConditionals(Check check) {
        return check.whetherHaveBrand(brand);
    }
}
