package checkout;

import java.util.Objects;

public class Trademark {
    final String name;

    public Trademark(String name) {
        this.name = name;
    }

    public static Trademark getDefaultOutlet() {
        return new Trademark("Default");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trademark trademark = (Trademark) o;
        return Objects.equals(name, trademark.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
