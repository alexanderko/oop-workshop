package checkout;

public interface Condition {
    boolean checkCondition(Check check);
}
/*

class TotalCost  implements Condition {
    @Override
    public boolean checkCondition(Check check) {
        return (10 <= check.getTotalCost());
    }
}

class ByCategory implements Condition{
    @Override
    public boolean checkCondition(Check check){
        boolean marker = false;
        for(Product p : check.getProducts()){
            if (p.category == Category.MILK) marker = true;
        }
        return marker;
    }
}

class ByBrand implements Condition{
    @Override
    public boolean checkCondition(Check check){
        boolean marker = false;
        for(Product p : check.getProducts()){
            if (p.brand == Brand.VOLOSHKOVE_POLE) marker = true;
        }
        return marker;
    }
}*/
