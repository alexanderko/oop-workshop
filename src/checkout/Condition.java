package checkout;

public interface Condition<T> {
    boolean test(T t);
}
