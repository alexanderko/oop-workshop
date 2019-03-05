package checkout;

import java.util.Objects;

public class Product {
    final int price;
    final String name;
    Category category;
    final Outlet outlet;

    public Product(int price, String name, Category category, Outlet outlet) {
        this.price = price;
        this.name = name;
        this.category = category;
        this.outlet = outlet;
    }

    public Product(int price, String name, Category category) {
        this(price, name, category, Outlet.getDefaultOutlet());
    }

    public Product(int price, String name) {
        this(price, name, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price &&
                Objects.equals(name, product.name) &&
                category == product.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, name, category);
    }
}
