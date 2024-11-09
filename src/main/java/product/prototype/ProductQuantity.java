package product.prototype;

import java.util.Objects;

public class ProductQuantity {
    private static final String PRODUCT_QUANTITY_FORMAT = "상품재고{프로모션=%d, 일반=%d}";
    public int promotion;
    public int normal;

    public ProductQuantity(int promotion, int normal) {
        this.promotion = promotion;
        this.normal = normal;
    }

    public int stock() {
        return promotion + normal;
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
