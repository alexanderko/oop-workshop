package checkout;

import java.time.LocalDate;

public class Offer {
    private LocalDate expiredDate;
    private Reward rewardType;
    private Condition conditionType;


    public Offer(LocalDate expiredDate, Reward rewardType, Condition conditionType) {
        this.expiredDate = expiredDate;
        this.rewardType = rewardType;
        this.conditionType = conditionType;
    }

    public void apply(Check check) {
        if(expiredDate.isAfter(LocalDate.now()) && conditionType.checkCondition(check)){
            rewardType.applyReward(check, conditionType.getCostForCondition(check));
        }
    }
}
