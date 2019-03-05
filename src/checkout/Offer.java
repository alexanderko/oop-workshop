package checkout;


import java.time.LocalDate;

public abstract class Offer {
    public LocalDate expirationDate;

    public Offer(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    void applyOffer(Check check, LocalDate currentDate) {
        if (currentDate.isBefore(expirationDate) || currentDate.equals(expirationDate)) {
            if (isSatisfiedConditionals(check)) {
                applyReward(check);
            }
        }
    }

    public abstract boolean isSatisfiedConditionals(Check check);

    public abstract void applyReward(Check check);
}
