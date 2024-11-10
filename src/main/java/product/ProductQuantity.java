package product;

import java.util.Objects;

public class ProductQuantity {
    private static final String PRODUCT_QUANTITY_FORMAT = "상품재고{프로모션=%d, 일반=%d}";
    private static final String TARGET_STOCK_EXCEEDS_ERROR_MESSAGE = "비교 대상의 재고가 더 많습니다.";
    private static final String EMPTY_STOCK_ERROR_MESSAGE = "재고가 부족합니다.";

    private final Quantity promotion;
    private final Quantity normal;
    
    public ProductQuantity(Quantity promotion, Quantity normal) {
        this.promotion = promotion;
        this.normal = normal;
    }

    public Quantity stock() {
        return promotion.plus(normal);
    }

    protected ProductQuantity calculateDifference(ProductQuantity quantity) {
        if(promotion.isLessThan(quantity.promotion)
                || normal.isLessThan(quantity.normal))
            throw new IllegalArgumentException(TARGET_STOCK_EXCEEDS_ERROR_MESSAGE);

        return new ProductQuantity(
                promotion.minus(quantity.promotion),
                normal.minus(quantity.normal)
        );
    }

    protected ProductQuantity decrease(Quantity quantity) {
        if(stock().isLessThan(quantity))
            throw new IllegalStateException(EMPTY_STOCK_ERROR_MESSAGE);

        if (promotion.equals(quantity) || promotion.isGreaterThan(quantity))
            return new ProductQuantity(promotion.minus(quantity), normal);
        return new ProductQuantity(new Quantity(0), normal.plus(promotion).minus(quantity));
    }

    protected PromotionBundles bundleUp(PromotionType type) {
        return new PromotionBundles(promotion, type);
    }

    public boolean isOnlyPromotionQuantity() {
        return Quantity.isEmpty(normal)
                && promotion.isGreaterThan(Quantity.EMPTY);
    }

    public boolean isOnlyNormalQuantity() {
        return Quantity.isEmpty(promotion)
                && normal.isGreaterThan(Quantity.EMPTY);
    }

    public boolean hasNormalQuantity() {
        return !Quantity.isEmpty(normal);
    }

    protected Quantity getNormal() {
        return normal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductQuantity that = (ProductQuantity) o;
        return promotion.amount == that.promotion.amount && normal.amount == that.normal.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(promotion.amount, normal.amount);
    }

    @Override
    public String toString() {
        return PRODUCT_QUANTITY_FORMAT.formatted(promotion.amount, normal.amount);
    }
}
