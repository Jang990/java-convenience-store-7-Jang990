package product.prototype;

public class NEWProduct {
    private static final String REQUESTING_EMPTY_QUANTITY_ERROR_MESSAGE = "0개를 구매할 수 없습니다.";
    private static final String EMPTY_STOCK_ERROR_MESSAGE = "재고가 부족합니다.";
    private static final int EMPTY_QUANTITY = 0;

    private final String name;
    private final int price;
    private ProductQuantity productQuantity;

    public NEWProduct(String name, int price, ProductQuantity productQuantity) {
        this.name = name;
        this.price = price;
        this.productQuantity = productQuantity;
    }

    public OrderLine purchase(int quantity) {
        if(quantity == EMPTY_QUANTITY)
            throw new IllegalArgumentException(REQUESTING_EMPTY_QUANTITY_ERROR_MESSAGE);
        if(productQuantity.stock() < quantity)
            throw new IllegalStateException(EMPTY_STOCK_ERROR_MESSAGE);

        ProductQuantity purchaseQuantity = calculatePurchaseQuantity(quantity);
        decreaseProductQuantity(quantity);
        return new OrderLine(name, price, purchaseQuantity);
    }

    private void decreaseProductQuantity(int quantity) {
        productQuantity = productQuantity.decrease(quantity);
    }

    private ProductQuantity calculatePurchaseQuantity(int quantity) {
        ProductQuantity quantityAfterPurchase = productQuantity.decrease(quantity);
        return productQuantity.calculateDifference(quantityAfterPurchase);
    }

    public ProductQuantity getStock() {
        return productQuantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
