package checkout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Offer implements WithExpirationDate {
    private List<Condition<Check>> conditions = new ArrayList<>();
    private List<Reward<Check>> rewards = new ArrayList<>();
    private final LocalDate expiredDate;

    public Offer(Reward<Check> reward, LocalDate expiredDate) {
        this.expiredDate = expiredDate;
        rewards.add(reward);
    }

    public Offer(Condition<Check> condition, Reward<Check> reward, LocalDate expiredDate) {
        this.expiredDate = expiredDate;
        conditions.add(condition);
        rewards.add(reward);
    }

    public void addCondition(Condition<Check> condition) {
        conditions.add(condition);
    }

    public void addReward(Reward<Check> reward) {
        rewards.add(reward);
    }

    public void apply(Check check) {
        if (conditions.isEmpty() || conditions.stream().allMatch(checkPredicate -> checkPredicate.test(check))) {
            rewards.forEach(reward -> reward.apply(check));
        }
    }

    @Override
    public boolean isExpired(LocalDate currentDate) {
        if (expiredDate == null) {
            return false;
        } else {
            return currentDate.isAfter(expiredDate);
        }
    }
}
