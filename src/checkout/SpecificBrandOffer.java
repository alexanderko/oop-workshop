package checkout;

import java.time.LocalDate;

public class SpecificBrandOffer extends Offer {
    final Brand brand;
    final double discount;

    public SpecificBrandOffer(Brand milk, int i) {
        this.brand = milk;
        this.discount = i/100.0;
        this.expirationDate = LocalDate.of(2020, 1,1);
    }

    @Override
    public void apply(Check check){
        Double points = check.getPointsForBrand(this.brand)*discount*10;
        check.addDiscount(points.intValue());
    }

    @Override
    public boolean isOfferavAilable() {
        if (this.expirationDate.isAfter(todayDate))
            return true;
        else return false;
    }
}
