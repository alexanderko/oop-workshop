package checkout;

public class Discount implements Reward {
    @Override
    public void apply(Check check){
        Double points = check.getPointsForBrand(Brand.VOLOSHKOVE_POLE)*0.5*10;
        check.addDiscount(points.intValue());
    }
}
