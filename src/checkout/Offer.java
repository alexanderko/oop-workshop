package checkout;

import java.time.LocalDate;

public abstract class Offer {
    private LocalDate expiredDate;

    public Offer(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }
    public abstract void applyAfterCheck(Check check);
    public void apply(Check check) {
        if(expiredDate.isAfter(LocalDate.now())){
            applyAfterCheck(check);
        }
    }
}
