package checkout;


import java.time.LocalDate;

public class Offer {
    private LocalDate expiresOn;
    private Reward rewardType;
    private Condition conditionType;

    public Offer(LocalDate expiresOn, Reward rewardType, Condition conditionType) {
        this.rewardType = rewardType;
        this.expiresOn = expiresOn;
        this.conditionType = conditionType;
    }

    void applyOffer(Check check, LocalDate currentDate) {
        if (currentDate.isBefore(expiresOn) || currentDate.equals(expiresOn)) {
            if (isSatisfy(check)) {
                applyReward(check);
            }
        }
    }

    public void setExpiration(LocalDate expiresOn) {
        this.expiresOn = expiresOn;
    }

    public void setRewardType(Reward rewardType) {
        this.rewardType = rewardType;
    }

    public void setConditionType(Condition conditionType) {
        this.conditionType = conditionType;
    }

    private boolean isSatisfy(Check check) {
        return conditionType.isSatisfy(check);
    }

    private void applyReward(Check check) {
        rewardType.applyReward(check);
    }
}
