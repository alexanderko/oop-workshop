package checkout;

public class ByCategoryCondition implements Condition{
    final Category category;
    final float count;

    public ByCategoryCondition(Category category, float count){
        this.category = category;
        this.count = count;
    }

    public boolean applyCondition(Check check){
        return check.getCountProductsByCategory(category) == count;
    }
}
