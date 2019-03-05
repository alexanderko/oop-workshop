package checkout;

public class OfferWithStrategy {
    Reward reward;
    Condition condition;
    public OfferWithStrategy(Reward reward , Condition condition){
            this.condition = condition;
            this.reward = reward;
    }


    public void applyStrategy(Check check){
        if(condition.checkCondition(check)) reward.apply(check);
    }

}
