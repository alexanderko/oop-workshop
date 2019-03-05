package checkout;

import java.time.LocalDate;

public abstract class Offer {
    public final LocalDate todayDate = LocalDate.now();
    public LocalDate expirationDate;
    public abstract void apply(Check check);
    public abstract  boolean isOfferavAilable();
}
