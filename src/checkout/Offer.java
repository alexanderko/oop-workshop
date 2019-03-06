package checkout;

import checkout.offer_interfaces.Condition;
import checkout.offer_interfaces.Reward;
import java.time.LocalDate;

public class Offer {

    private LocalDate expiresDate;
    private Reward reward;
    private Condition condition;

    public Offer(LocalDate expireDate, Reward rewardType) {
        this(expireDate, rewardType, null );
    }

    public Offer(LocalDate expireDate, Reward rewardType, Condition conditionType) {
        this.expiresDate = expireDate;
        this.reward = rewardType;
        this.condition = conditionType;
    }

    void applyOffer(Check check) {
        if (this.expiresDate.isAfter(check.getDateOfCheck()))
            reward.applyReward(check);
    }

    boolean isOfferValid(Check check) {
        return (condition == null) || condition.checkCondition(check);
    }

}