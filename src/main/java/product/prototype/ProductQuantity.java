package product.prototype;

import java.util.Objects;

public class ProductQuantity {
    private static final String PRODUCT_QUANTITY_FORMAT = "상품재고{프로모션=%d, 일반=%d}";
    private static final String TARGET_STOCK_EXCEEDS_ERROR_MESSAGE = "비교 대상의 재고가 더 많습니다.";
    public int promotion;
    public int normal;

    public ProductQuantity(int promotion, int normal) {
        this.promotion = promotion;
        this.normal = normal;
    }

    public int stock() {
        return promotion + normal;
    }

    protected ProductQuantity calculateDifference(ProductQuantity quantity) {
        if(quantity.stock() > this.stock()
                || quantity.promotion > promotion
                || quantity.normal > normal)
            throw new IllegalArgumentException(TARGET_STOCK_EXCEEDS_ERROR_MESSAGE);

        return null;
    }

    protected ProductQuantity decrease(int quantity) {
        if (promotion >= quantity) {
            return new ProductQuantity(promotion - quantity, normal);
        }

        return new ProductQuantity(0, normal - quantity + promotion);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductQuantity that = (ProductQuantity) o;
        return promotion == that.promotion && normal == that.normal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(promotion, normal);
    }

    @Override
    public String toString() {
        return PRODUCT_QUANTITY_FORMAT.formatted(promotion, normal);
    }
}
