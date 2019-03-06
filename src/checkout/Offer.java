package checkout;

import java.time.LocalDate;

public class Offer {
    private LocalDate expiredDate;
    private Reward reward;
    private Condition condition;
    private LocalDate todayDate = LocalDate.of(2019, 3, 6);

    public Offer(LocalDate expiredDate, Reward rewardType, Condition condition) {
        this.expiredDate = expiredDate;
        this.reward = rewardType;
        this.condition = condition;
    }

    public void apply(Check check) {
        if (expiredDate.isAfter(todayDate) && condition.checkCondition(check)) {
            reward.applyReward(check, condition.getCostForCondition(check));
        }
    }
}
