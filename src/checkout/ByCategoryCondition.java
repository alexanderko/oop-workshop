package checkout;

public class ByCategoryCondition implements Condition {

    private Category category;

    public ByCategoryCondition(Category category) {
        this.category = category;
    }

    @Override
    public boolean isSatisfy(Check check) {
        return check.getCostByCategory(category) != 0;
    }
}
