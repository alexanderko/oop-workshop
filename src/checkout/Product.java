package checkout;

public class Product {
    final int price;
    final String name;
    Category category;
    Outlet outlet;

    public Product(int price, String name, Category category, Outlet outlet) {
        this.price = price;
        this.name = name;
        this.category = category;
        this.outlet = outlet;
    }

    public Product(int price, String name) {
        this(price, name, null, null);
    }
}
