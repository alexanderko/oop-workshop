package checkout;

import java.util.Objects;

public class Outlet {
    final String name;

    public Outlet(String name) {
        this.name = name;
    }

    public static Outlet getDefaultOutlet() {
        return new Outlet("Default");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outlet outlet = (Outlet) o;
        return Objects.equals(name, outlet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
