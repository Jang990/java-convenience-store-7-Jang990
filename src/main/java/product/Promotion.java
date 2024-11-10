package product;

import product.exception.PromotionException;

import java.time.LocalDate;

public class Promotion {
    private final PromotionType promotionType;
    private final PromotionDuration duration;
    private final String name;

    public Promotion(
            PromotionType promotionType,
            PromotionDuration duration,
            String name) {
        this.promotionType = promotionType;
        this.duration = duration;
        this.name = name;
    }

    public boolean isAvailable(LocalDate now) {
        return duration.isWithIn(now);
    }

    @Override
    public String toString() {
        return name;
    }

    public Quantity calculateFree(ProductQuantity requested) throws PromotionException {
        if(requested.isOnlyNormalQuantity())
            return Quantity.EMPTY;

        PromotionBundles promotionBundles = requested.bundleUp(promotionType);
        if (requested.isOnlyPromotionQuantity() && promotionBundles.getShortFall().equals(promotionType.getFree()))
            throw new PromotionException(
                    "추가로 구매한다면 상품을 무료로 받을 수 있습니다.",
                    promotionType.getFree()
            );

        if (requested.hasNormalQuantity() || promotionBundles.hasRemainder())
            throw new PromotionException(
                    "몇 개는 프로모션이 적용되지 않아 정가로 구매해야 합니다.",
                    promotionBundles.getRemainder().plus(requested.getNormal())
            );

        return promotionType.getFree()
                .times(promotionBundles.getAmount());
    }
}
