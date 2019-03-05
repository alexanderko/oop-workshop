package checkout;

public class Condition_ByCategory implements Condition {

    private Category category;

    public Condition_ByCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean isSatisfiedConditionals(Check check) {
        return check.getCostByCategory(category) != 0;
    }
}
