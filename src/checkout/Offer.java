package checkout;

import java.time.LocalDate;
import java.time.Month;


public abstract class Offer {



    public void applyOffer(Check check) {
        if (this.expiresDate.compareTo(LocalDate.now()) >= 0) {
            this.applyReword(check);
        } else {
            check.addPoints(0);
        }
    }

    public abstract void applyReword(Check check);

    protected LocalDate expiresDate = LocalDate.of(2020, Month.JANUARY, 16);

    public void setExpireDate(int year, Month month, int day) {
        this.expiresDate = LocalDate.of(year, month, day);
    }
}

