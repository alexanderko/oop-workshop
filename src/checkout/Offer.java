package checkout;

public abstract class Offer {
    //public Time ExplorationTime;

    void applyOffer(Check check) {
        if (isSatisfiedConditionals(check)) {
            applyReward(check);
        }
    }

    public abstract boolean isSatisfiedConditionals(Check check);

    public abstract void applyReward(Check check);
}
