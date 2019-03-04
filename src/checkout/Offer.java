package checkout;


import java.time.LocalDate;

public abstract class Offer {
    public LocalDate explorationTime;

    void applyOffer(Check check, LocalDate currentDate) {
        if (currentDate.isBefore(explorationTime)) {
            if (isSatisfiedConditionals(check)) {
                applyReward(check);
            }
        }
    }

    public abstract boolean isSatisfiedConditionals(Check check);

    public abstract void applyReward(Check check);
}
