package product.prototype;

public class NEWProduct {
    private int promotionStock;
    private int normalStock;

    public NEWProduct(int promotionStock, int normalStock) {
        this.promotionStock = promotionStock;
        this.normalStock = normalStock;
    }

    public void buy(int quantity) {
        if(stock() < quantity)
            throw new IllegalStateException("재고가 부족합니다.");

        promotionStock -= 2;
        normalStock -= 1;
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
}
