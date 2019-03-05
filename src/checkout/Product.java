package checkout;

public class Product {
    final int price;
    final String name;
    Category category;
    Brand brand;

    public Product(int price, String name, Category category, Brand brand) {
        this.price = price;
        this.name = name;
        this.category = category;
        this.brand = brand;
    }

    public Product(int price, String name, Category category) {
        this(price, name, category, null);
    }

    public Product(int price, String name) {
        this(price, name, null);
    }
}
