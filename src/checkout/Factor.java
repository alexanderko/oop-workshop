package checkout;

public class Factor implements Reward {
    @Override
    public void apply(Check check){
        int points = check.getCostByCategory(Category.MILK);
        check.addPoints(points*1);
    }}
