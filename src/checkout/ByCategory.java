package checkout;

public class ByCategory implements Condition {
    Category category;

    public ByCategory(Category category) {
        setCategory(category);
    }

    @Override
    public boolean checkCondition(Check check) {
        boolean marker = false;
        for(Product p : check.getProducts()) {
            if (p.category == category) marker = true;
        }
        return marker;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
