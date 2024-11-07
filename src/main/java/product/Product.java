package product;

import product.exception.PromotionException;

public class Product {
    private int promotionStock;
    private int normalStock;
    private final Promotion promotion;

    public Product(int normalStock) {
        this.normalStock = normalStock;
        promotion = null;
        promotionStock = 0;
    }

    public Product(int normalStock, int promotionStock, Promotion promotion) {
        this.normalStock = normalStock;
        this.promotionStock = promotionStock;
        this.promotion = promotion;
    }

    public void sale(int saleQuantity) throws PromotionException {
        if(normalStock < saleQuantity)
            throw new IllegalStateException("재고가 부족합니다.");
        if(promotion.isAvailable() && promotionStock < saleQuantity)
            throw new PromotionException("프로모션을 적용할 수 있지만, 프로모션 재고가 부족합니다.");

        this.normalStock -= saleQuantity;
    }
}
