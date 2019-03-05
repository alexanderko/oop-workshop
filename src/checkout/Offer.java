package checkout;

import checkout.offer_interfaces.Condition;
import checkout.offer_interfaces.Reward;

import java.time.LocalDate;
import java.time.Month;


public class Offer {

    private Reward reward;
    private Condition condition;
    private LocalDate expiresDate = LocalDate.of(2020, Month.JANUARY, 16);
    private LocalDate dateOfCheck = LocalDate.now();

    public Offer(Reward rewardType, Condition conditionType) {
        this.reward = rewardType;
        this.condition = conditionType;
    }

    public Offer(Reward rewardType, Condition conditionType, LocalDate expiresDate) {
        this.reward = rewardType;
        this.condition = conditionType;
        this.expiresDate = expiresDate;
    }

    public Offer(Reward rewardType) {
        this.reward = rewardType;
        this.condition = null;
    }

    public void applyOffer(Check check) {
        if (this.expiresDate.compareTo(this.dateOfCheck) >= 0)
            reward.applyReward(check);
    }

    public boolean isOfferValid(Check check) {
        return (condition == null) || condition.checkCondition(check);
    }

    public void setDateOfCheck(LocalDate date) {
        this.dateOfCheck = date;
    }

}