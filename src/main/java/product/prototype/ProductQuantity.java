package product.prototype;

public class ProductQuantity {
    private final String PRODUCT_QUANTITY_FORMAT = "상품재고{프로모션=%d, 일반=%d}";
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
    public String toString() {
        return PRODUCT_QUANTITY_FORMAT.formatted(promotion, normal);
    }
}
