package product;

import money.Money;

public class Product {
    private static final String REQUESTING_EMPTY_QUANTITY_ERROR_MESSAGE = "0개를 구매할 수 없습니다.";
    private static final int EMPTY_QUANTITY = 0;

    private final String name;
    private final Money price;
    private final Promotion promotion;
    private ProductQuantity productQuantity;

    public Product(
            String name, Money price,
            ProductQuantity productQuantity, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.promotion = promotion;
        this.productQuantity = productQuantity;
    }

    public Product(String name, Money price, ProductQuantity productQuantity) {
        this.name = name;
        this.price = price;
        this.productQuantity = productQuantity;
        promotion = null;
    }

    public OrderLine purchase(Quantity requested) {
        if(Quantity.isEmpty(requested))
            throw new IllegalArgumentException(REQUESTING_EMPTY_QUANTITY_ERROR_MESSAGE);

        ProductQuantity purchaseQuantity = calculatePurchaseQuantity(requested);
        productQuantity = productQuantity.decrease(requested);

        return new OrderLine(name, price, purchaseQuantity.stock(), Quantity.EMPTY);
    }

    private ProductQuantity calculatePurchaseQuantity(Quantity requested) {
        ProductQuantity quantityAfterPurchase = productQuantity.decrease(requested);
        return productQuantity.calculateDifference(quantityAfterPurchase);
    }

    public ProductQuantity getStock() {
        return productQuantity;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }
}
