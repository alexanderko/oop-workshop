package checkout;

public class Rewards {
    private Rewards() {}

    public static Reward<Check> addPoints(int points) {
        return item -> item.addPoints(points);
    }

    public static Reward<Check> factorPoints(int factor) {
        return item -> item.addPoints(item.getTotalCost() * (factor - 1));
    }

    public static Reward<Check> discount(int percent) {
        return item -> item.addDiscount(percent);
    }

    public static Reward<Check> factorPointsByCategory(int factor, Category category) {
        return item -> item.addPoints(item.getCostByCategory(category) * (factor - 1));
    }
}
