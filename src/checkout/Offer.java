package checkout;


import java.time.LocalDate;

public class Offer {
    private LocalDate expirationDate;
    private Reward rewardtType;

    public Offer(LocalDate expirationDate, Reward rewardType) {
        this.rewardtType = rewardType;
        this.expirationDate = expirationDate;
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
