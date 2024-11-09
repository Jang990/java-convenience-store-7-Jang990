package product.prototype;

public class NEWProduct {
    private static final String REQUESTING_EMPTY_QUANTITY_ERROR_MESSAGE = "0개를 구매할 수 없습니다.";
    private static final String EMPTY_STOCK_ERROR_MESSAGE = "재고가 부족합니다.";
    private static final int EMPTY_QUANTITY = 0;

    private final String name;
    private final int price;
    private final ProductQuantity productQuantity;

    public NEWProduct(String name, int price, ProductQuantity productQuantity) {
        this.name = name;
        this.price = price;
        this.productQuantity = productQuantity;
    }

    public OrderLine buy(int quantity) {
        if(quantity == EMPTY_QUANTITY)
            throw new IllegalArgumentException(REQUESTING_EMPTY_QUANTITY_ERROR_MESSAGE);
        if(productQuantity.stock() < quantity)
            throw new IllegalStateException(EMPTY_STOCK_ERROR_MESSAGE);

        if (productQuantity.promotion >= quantity) {
            productQuantity.promotion -= quantity;
            return new OrderLine(name, price, new ProductQuantity(quantity, 0));
        }

        int promotionCnt = productQuantity.promotion;
        quantity -= productQuantity.promotion;
        int normalCnt = quantity;
        productQuantity.promotion = 0;

        productQuantity.normal -= quantity;
        return new OrderLine(name, price, new ProductQuantity(promotionCnt, normalCnt));
    }

    public ProductQuantity getStock() {
        return productQuantity;
    }

    public int getPromotionStock() {
        return productQuantity.promotion;
    }

    public int getNormalStock() {
        return productQuantity.normal;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
