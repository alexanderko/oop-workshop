package checkout;

public class ByCategory implements Condition{
    @Override
    public boolean checkCondition(Check check){
        boolean marker = false;
        for(Product p : check.getProducts()){
            if (p.category == Category.MILK) marker = true;
        }
        return marker;
    }
}
