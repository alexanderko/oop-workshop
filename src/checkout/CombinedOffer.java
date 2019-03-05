package checkout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CombinedOffer extends Offer {
    private List<Predicate<Check>> conditions = new ArrayList<>();
    private List<Consumer<Check>> rewards = new ArrayList<>();

    public CombinedOffer(Predicate<Check> condition, Consumer<Check> reward, LocalDate expiredDate) {
        super(expiredDate);
        conditions.add(condition);
        rewards.add(reward);
    }

    public void addCondition(Predicate<Check> condition) {
        conditions.add(condition);
    }

    public void addReward(Consumer<Check> reward) {
        rewards.add(reward);
    }

    @Override
    public void apply(Check check) {
        if (conditions.isEmpty() || conditions.stream().allMatch(checkPredicate -> checkPredicate.test(check))) {
            rewards.forEach(worker -> worker.accept(check));
        }
    }
}
