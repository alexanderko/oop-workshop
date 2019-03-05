package checkout;

public class Conditions {
    private Conditions() {}

    public static Condition<Check> totalCostGreatThen(int value) {
        return check -> check.getTotalCost() > value;
    }

    public static Condition<Check> categoryEqualsTo(Category category) {
        return check -> check.getProducts().stream().anyMatch(product -> product.category.equals(category));
    }

    public static Condition<Check> hasProduct(Product product) {
        return check -> check.getProducts().stream().anyMatch(product::equals);
    }

    public static Condition<Check> hasTrademark(Trademark trademark) {
        return check -> check.getProducts().stream().map(product -> product.trademark).anyMatch(trademark::equals);
    }

}
