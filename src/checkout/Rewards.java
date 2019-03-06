package checkout;

import java.util.function.Predicate;

public class Rewards {
    private Rewards() {}

    public static Reward<Check> addPoints(int points) {
        return check -> check.addPoints(points);
    }

    public static Reward<Check> factorPoints(int factor) {
        return check -> check.addPoints(check.getTotalCost() * (factor - 1));
    }

    public static Reward<Check> discountForTrademark(int percent, Trademark trademark) {
        return discountBy(percent, product -> product.trademark.equals(trademark));
    }

    public static Reward<Check> discountByCategory(int percent, Category category) {
        return discountBy(percent, product -> product.category == category);
    }

    public static Reward<Check> factorPointsByCategory(int factor, Category category) {
        return factorPointsBy(factor, product -> product.category == category);
    }

    public static Reward<Check> factorPointsByTrademark(int factor, Trademark trademark) {
        return factorPointsBy(factor, product -> product.trademark.equals(trademark));
    }

    private static Reward<Check> discountBy(int percent, Predicate<Product> predicate) {
        return check -> check.addDiscountFor(percent, predicate);
    }

    private static Reward<Check> factorPointsBy(int factor, Predicate<Product> predicate) {
        return check -> check.addPoints(check.getCostBy(predicate) * (factor - 1));
    }
}
