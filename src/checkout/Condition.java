package checkout;

import java.util.function.Predicate;

public class Condition {
    private Condition() {}

    public static Predicate<Check> totalCostGreatThen(int value) {
        return check -> check.getTotalCost() > value;
    }

    public static Predicate<Check> categoryEqualsTo(Category category) {
        return check -> check.getProducts().stream().anyMatch(product -> product.category.equals(category));
    }

    public static Predicate<Check> hasProduct(Product product) {
        return check -> check.getProducts().stream().anyMatch(product::equals);
    }

    public static Predicate<Check> hasOutlet(Trademark trademark) {
        return check -> check.getProducts().stream().map(product -> product.trademark).anyMatch(trademark::equals);
    }

}
