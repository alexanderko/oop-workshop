package checkout;

public class Flat  implements Reward {
    @Override
    public void apply(Check check) {
        check.addPoints(20);
    }
}