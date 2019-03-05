package checkout;

public class SaleByCategoryReward implements Reward{
    final Category category;
    final int sale;
    public SaleByCategoryReward(Category category, int sale){
        this.category = category;
        this.sale = sale;
    }

    public void applyReward(Check check){
        check.addSaleByCategory(category, sale);
    }
}
