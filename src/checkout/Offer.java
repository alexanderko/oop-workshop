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

//    public Offer(Reward rewardType) {
//        this.reward = rewardType;
//        this.condition = null;
//    }
//
//    public Offer(Reward rewardType, Condition conditionType) {
//        this.reward = rewardType;
//        this.condition = conditionType;
//    }
//
//    public Offer(Reward rewardType, Condition conditionType, LocalDate expiresDate) {
//        this.reward = rewardType;
//        this.condition = conditionType;
//        this.expiresDate = expiresDate;
//    }

    public void applyOffer(Check check) {
        if (this.expiresDate.isAfter(check.getDateOfCheck()))
            reward.applyReward(check);
    }

    public boolean isOfferValid(Check check) {
        return (condition == null) || condition.checkCondition(check);
    }

}