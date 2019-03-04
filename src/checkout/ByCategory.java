package checkout;

public class ByCategory implements Condition {
    private Category category;

    public ByCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean inCondition(Check check) {
        return check.getCostByCategory(category) > 0;
    }

    @Override
    public int getPointsByCondition(Check check) {
        return check.getCostByCategory(category);
    }
}
