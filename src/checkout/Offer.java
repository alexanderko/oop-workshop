package checkout;

public abstract class Offer {
    public abstract void apply(Check check);
    public abstract boolean isActual();
}
