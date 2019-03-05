package checkout;

import checkout.offer_interfaces.Condition;
import checkout.offer_interfaces.Reward;

import java.time.LocalDate;
import java.time.Month;


public class Offer {

    private Reward reward;
    private Condition condition;

    public Offer(Reward rewardType, Condition conditionType) {
        this.reward = rewardType;
        this.condition = conditionType;
    }

    public Offer(Reward rewardType) {
        this.reward = rewardType;
        this.condition = null;
    }

    public void applyOffer(Check check) {
        reward.applyReward(check);
    }

    public boolean isOfferValid(Check check) {
        return (condition == null) || condition.checkCondition(check);
    }
//
//    public void applyOffer(Check check) {
//        if (this.expiresDate.compareTo(LocalDate.now()) >= 0) {
//            this.applyReword(check);
//        } else {
//            check.addPoints(0);
//        }
//    }

//    public abstract void applyReword(Check check);

//    protected LocalDate expiresDate = LocalDate.of(2020, Month.JANUARY, 16);

//    public void setExpireDate(int year, Month month, int day) {
//        this.expiresDate = LocalDate.of(year, month, day);
//    }
}

