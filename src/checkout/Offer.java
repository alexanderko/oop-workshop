package checkout;

import java.time.LocalDate;

public class Offer {
    private LocalDate expiredDate;
    private Reward reward;
    private Condition condition;


    public Offer(LocalDate expiredDate, Reward rewardType, Condition condition) {
        this.expiredDate = expiredDate;
        this.reward = rewardType;
        this.condition = condition;
    }

    public void apply(Check check) {
        if(expiredDate.isAfter(LocalDate.now()) && condition.checkCondition(check)){
            reward.applyReward(check, condition.getCostForCondition(check));
        }
    }
}
