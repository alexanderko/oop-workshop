package checkout;

import java.time.LocalDate;

public class AnyGoodsOffer extends Offer {
    public final int totalCost;
    public final int points;

    public AnyGoodsOffer(int totalCost, int points) {
        this.totalCost = totalCost;
        this.points = points;
        this.expirationDate = LocalDate.of(2020, 1,1);
    }

    @Override
    public void apply(Check check) {
        if (this.totalCost <= check.getTotalCost())
            check.addPoints(this.points);
    }


    @Override
    public boolean isOfferavAilable(){
        if (this.expirationDate.isAfter(todayDate))
            return true;
        else return false;
    }
}
