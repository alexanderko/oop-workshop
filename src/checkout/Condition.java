package checkout;

public interface Condition {
    boolean inCondition(Check check);

    int getPointsByCondition(Check check);
}
