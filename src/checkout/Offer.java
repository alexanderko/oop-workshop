package checkout;

import java.time.LocalDate;

public abstract class Offer implements WithExpirationDate {
    public abstract void apply(Check check);
    final LocalDate expiredDate;

    public Offer(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Override
    public boolean isExpired(LocalDate currentDate) {
        if (expiredDate == null) {
            return false;
        } else {
            return currentDate.isAfter(expiredDate);
        }
    }
}
