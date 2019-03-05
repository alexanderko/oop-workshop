package checkout;

public interface Condition {
    boolean checkCondition(Check check);

    int getCostForCondition(Check check);
}
