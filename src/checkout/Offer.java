package checkout;


import java.time.LocalDate;

public abstract class Offer {
    private LocalDate expirationDate;
    private Reward rewardtType;
    private Condition conditionType;

    public Offer(LocalDate expirationDate, Reward rewardType, Condition conditionType) {
        this.rewardtType = rewardType;
        this.expirationDate = expirationDate;
        this.conditionType = conditionType;
    }

    void applyOffer(Check check, LocalDate currentDate) {
        if (currentDate.isBefore(expirationDate) || currentDate.equals(expirationDate)) {
            if (isSatisfiedConditionals(check)) {
                applyReward(check);
            }
        }
    }

    private boolean isSatisfiedConditionals(Check check) {
        return true;
    }

    private void applyReward(Check check) {
        rewardtType.applyReward(check);
    }
}
