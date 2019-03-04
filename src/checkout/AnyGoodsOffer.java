package checkout;

public class AnyGoodsOffer extends Offer {
    public final int totalCost;
    public final int points;

    public AnyGoodsOffer(int totalCost, int points) {
        this.totalCost = totalCost;
        this.points = points;
    }

    @Override
    public void apply(Check check) {
        if (totalCost <= check.getTotalCost())
            check.addPoints(points);
    }
}
