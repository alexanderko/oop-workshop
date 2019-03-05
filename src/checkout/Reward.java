package checkout;

public interface Reward<T extends Check> {
    void apply(T t);
}
