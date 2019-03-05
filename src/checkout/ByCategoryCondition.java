package checkout;

public class ByCategoryCondition implements Condition {

    private final int totalCost;
    private Category category;

    public ByCategoryCondition(Category category, int totalCost) {
        this.category = category;
        this.totalCost = totalCost;
    }

    @Override
    public boolean isSatisfy(Check check) {
        return check.getCostByCategory(category) >= totalCost;
    }
}
