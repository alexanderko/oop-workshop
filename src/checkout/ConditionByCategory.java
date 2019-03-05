package checkout;

public class ConditionByCategory implements Condition {
    Category category;
    public ConditionByCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean checkCondition(Check check) {
        if(check.getCostByCategory(category) != 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int getCostForCondition(Check check) {
        return check.getCostByCategory(category);
    }
}
