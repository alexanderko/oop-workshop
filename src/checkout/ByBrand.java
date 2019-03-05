package checkout;

public class ByBrand implements Condition {
    @Override
    public boolean checkCondition(Check check) {
        boolean marker = false;
        for (Product p : check.getProducts()) {
            if (p.brand == Brand.VOLOSHKOVE_POLE) marker = true;
        }
        return marker;
    }
}
