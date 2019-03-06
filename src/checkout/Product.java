package checkout;

public class Product {
    final int price;
    final String name;
    Category category;
    TradeMark tradeMark;

    public Product(int price, String name, Category category, TradeMark tradeMark) {
        this.price = price;
        this.name = name;
        this.category = category;
        this.tradeMark = tradeMark;
    }

    public Product(int price, String name) {
        this(price, name, null, null);
    }
}
