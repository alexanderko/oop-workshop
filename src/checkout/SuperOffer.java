package checkout;

import java.util.Date;

public class SuperOffer extends Offer {

    final Condition condition;
    final Reward reward;
    private final Date expirationDate;


    public SuperOffer(Condition condition, Reward reward, Date expirationDate) {
            this.condition = condition;
            this.reward = reward;
            this.expirationDate = new Date(expirationDate.getYear() - 1900, expirationDate.getMonth(), expirationDate.getDate());
        }

    @Override
    public void apply(Check check) {
        if (isValid(expirationDate) && condition.inCondition(check)) {
            reward.useReward(check, condition);
        }
    }

}
