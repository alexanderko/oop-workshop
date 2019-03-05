package checkout;

public class FactorReward implements Reward{
    final Category category;
    final Trademark trademark;
    final int factor;

    public FactorReward(Category category, int factor){
        this.category = category;
        this.factor = factor;
        this.trademark = null;
    }

    public FactorReward(Trademark trademark, int factor){
        this.category = null;
        this.factor = factor;
        this.trademark = trademark;
    }

    public void applyReward(Check check){
        int points;
        if (trademark ==  null)
            points = check.getCostByCategory(this.category);
        else
            points = check.getCostByTrademark(this.trademark);
        check.addPoints(points * (this.factor - 1));
    }
}
