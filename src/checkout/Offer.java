package checkout;

import java.time.LocalDate;

public abstract class Offer implements WithExpirationDate {
    public abstract void apply(Check check);

    @Override
    public boolean isExpired(LocalDate currentDate) {
        return false;
    }
}
