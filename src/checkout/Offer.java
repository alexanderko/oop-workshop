package checkout;

import java.util.Date;

public abstract class Offer {
    public abstract void apply(Check check);
    public  boolean isValid(Date expirationDate) {
        return expirationDate.after(new Date());
    };
}
