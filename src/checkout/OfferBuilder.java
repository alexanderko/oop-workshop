package checkout;

import java.time.LocalDate;

public class OfferBuilder {

    private Offer offer;

    public OfferBuilder() {
        this.offer = new Offer(null, null, null);
    }

    public OfferBuilder setExpiration(LocalDate expiration) {
        offer.setExpiration(expiration);
        return this;
    }

    public Offer build() {
        return offer;
    }

    //REWARDS
    public OfferBuilder flatReward(int totalCost, int points) {
        offer.setRewardType(new FlatReward(totalCost, points));
        return this;
    }

    public OfferBuilder factorReward(Category category, int factor) {
        offer.setRewardType(new FactorReward(category, factor));
        return this;
    }

    public OfferBuilder discountReward(int discount) {
        offer.setRewardType(new DiscountReward(discount));
        return this;
    }

    //CONDITIONS
    public OfferBuilder costByTotalCost(int totalCost) {
        offer.setConditionType(new TotalCostCondition(totalCost));
        return this;
    }

    public OfferBuilder costByCategory(Category category, int totalCost) {
        offer.setConditionType(new ByCategoryCondition(category, totalCost));
        return this;
    }

    public OfferBuilder costByTrademark(Trademark trademark) {
        offer.setConditionType(new ByTrademarksCondition(trademark));
        return this;
    }
}
