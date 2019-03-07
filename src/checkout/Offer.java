package checkout;

import java.time.LocalDate;

public class Offer {
    Reward reward;
    Condition condition;
    private LocalDate expirationDate;

    public Offer(Reward reward , Condition condition, LocalDate expirationDate) {
        this.condition = condition;
        this.reward = reward;
        this.expirationDate = expirationDate;
    }

    public void apply(Check check) {
        if(condition.checkCondition(check) && isOfferAvailable(check.getTodayDate())) reward.apply(check);
    }

    public boolean isOfferAvailable(LocalDate todayDate) {
        return (expirationDate.isAfter(todayDate));
    }
}
