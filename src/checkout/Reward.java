package checkout;

public interface Reward {
    void apply(Check check);
}

/*public class Flat  implements Reward {
    @Override
    public void apply(Check check) {
        check.addPoints(20);
    }
}

public class Factor implements Reward {
    @Override
    public void apply(Check check){
        int points = check.getCostByCategory(Category.MILK);
        check.addPoints(points*2);
    }}
public class Discount implements Reward {
        @Override
        public void apply(Check check){
            Double points = check.getPointsForBrand(Brand.VOLOSHKOVE_POLE)*0.5*10;
            check.addDiscount(points.intValue());
        }
    }*/

