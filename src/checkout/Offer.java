package checkout;

import java.time.LocalDate;

public class Offer {
    Reward reward;
    Condition condition;
    private LocalDate expirationDate;

    public Offer(Reward reward , Condition condition, LocalDate expirationDate){
        this.condition = condition;
        this.reward = reward;
        this.expirationDate = expirationDate;
    }

    public Offer(Reward reward , Condition condition){
        this.condition = condition;
        this.reward = reward;
        this.expirationDate = LocalDate.of(2019, 3, 7);
    }

    public void apply(Check check){
        if(condition.checkCondition(check)) reward.apply(check);
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
