package checkout;

public class ByBrand implements Condition {
    Brand brand;

    public ByBrand(Brand brand){
        setBrand(brand);
    }
    @Override
    public boolean checkCondition(Check check) {
        boolean marker = false;
        for (Product p : check.getProducts()) {
            if (p.brand == brand) marker = true;
        }
        return marker;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
