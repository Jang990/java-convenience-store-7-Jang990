package product.prototype;

public class NEWProduct {
    private static final String REQUESTING_EMPTY_QUANTITY_ERROR_MESSAGE = "0개를 구매할 수 없습니다.";
    private static final String EMPTY_STOCK_ERROR_MESSAGE = "재고가 부족합니다.";
    private static final int EMPTY_QUANTITY = 0;

    private final String name;
    private final int price;
    private final ProductQuantity stock;

    public NEWProduct(String name, int price, ProductQuantity stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void buy(int quantity) {
        if(quantity == EMPTY_QUANTITY)
            throw new IllegalArgumentException(REQUESTING_EMPTY_QUANTITY_ERROR_MESSAGE);
        if(stock() < quantity)
            throw new IllegalStateException(EMPTY_STOCK_ERROR_MESSAGE);

        if (stock.promotion >= quantity) {
            stock.promotion -= quantity;
            return;
        }
        quantity -= stock.promotion;
        stock.promotion = 0;

        stock.normal -= quantity;
    }

    public int getPromotionStock() {
        return stock.promotion;
    }

    public int getNormalStock() {
        return stock.normal;
    }

    private int stock() {
        return stock.promotion + stock.normal;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
