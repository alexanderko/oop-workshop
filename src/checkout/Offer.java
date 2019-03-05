package checkout;

import java.time.LocalDate;

public class Offer {
    public Condition condition;
    public Reward reward;
    public LocalDate expirationDate;
    public Offer(Condition condition, Reward reward, LocalDate expirationDate){
        this.condition = condition;
        this.reward = reward;
        this.expirationDate = expirationDate;
    }

    public Offer(Condition condition, Reward reward){
        this(condition, reward, null);
    }

    public Offer() {
    }

    public void apply(Check check){
        if (isActual()){
            if (condition.applyCondition(check)){
                reward.applyReward(check);
            }
        }
    }

    public boolean isActual(){
        if (this.expirationDate != null) {
            return expirationDate.isAfter(LocalDate.now());
        } else {
            return true;
        }
    }
}
