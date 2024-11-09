package product.prototype;

public class NEWProduct {
    private static final String REQUESTING_EMPTY_QUANTITY_ERROR_MESSAGE = "0개를 구매할 수 없습니다.";
    private static final String EMPTY_STOCK_ERROR_MESSAGE = "재고가 부족합니다.";
    private static final int EMPTY_QUANTITY = 0;

    private final String name;
    private final int price;
    private int promotionStock;
    private int normalStock;

    public NEWProduct(String name, int price, int promotionStock, int normalStock) {
        this.name = name;
        this.price = price;
        this.promotionStock = promotionStock;
        this.normalStock = normalStock;
    }

    public void buy(int quantity) {
        if(quantity == EMPTY_QUANTITY)
            throw new IllegalArgumentException(REQUESTING_EMPTY_QUANTITY_ERROR_MESSAGE);
        if(stock() < quantity)
            throw new IllegalStateException(EMPTY_STOCK_ERROR_MESSAGE);

        if (promotionStock >= quantity) {
            promotionStock -= quantity;
            return;
        }
        quantity -= promotionStock;
        promotionStock = 0;

        normalStock -= quantity;
    }

    public int getPromotionStock() {
        return promotionStock;
    }

    public int getNormalStock() {
        return normalStock;
    }

    private int stock() {
        return promotionStock + normalStock;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
