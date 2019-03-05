package checkout;

import java.util.function.Consumer;

public class Reward {
    private Reward() {}

    public static Consumer<Check> addPoints(int points) {
        return item -> item.addPoints(points);
    }

    public static Consumer<Check> factorPoints(int factor) {
        return item -> item.addPoints(item.getTotalCost() * (factor - 1));
    }

    public static Consumer<Check> discount(int percent) {
        return item -> item.addDiscount(percent);
    }
}
