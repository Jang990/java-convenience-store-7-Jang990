package store.product;

import store.product.exception.MissedPromotionBenefitException;
import store.product.exception.PartialProductExclusionException;
import store.product.exception.PromotionException;

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

    public Quantity calculateFree(ProductQuantity requested) throws PromotionException {
        if(requested.isOnlyNormalQuantity())
            return Quantity.EMPTY;

        PromotionBundles promotionBundles = requested.bundleUp(promotionType);
        if (requested.isOnlyPromotionQuantity() && promotionBundles.getShortFall().equals(promotionType.getFree()))
            throw new MissedPromotionBenefitException(
                    "추가로 구매한다면 상품을 무료로 받을 수 있습니다.",
                    promotionType.getFree()
            );

        if (requested.hasNormalQuantity() || promotionBundles.hasRemainder())
            throw new PartialProductExclusionException(
                    "몇 개는 프로모션이 적용되지 않아 정가로 구매해야 합니다.",
                    promotionBundles.getRemainder().plus(requested.getNormal())
            );

        return promotionType.getFree()
                .times(promotionBundles.getAmount());
    }

    public Quantity calculateFreeWithoutException(ProductQuantity requested) {
        if(requested.isOnlyNormalQuantity())
            return Quantity.EMPTY;

        PromotionBundles promotionBundles = requested.bundleUp(promotionType);
        return promotionType.getFree()
                .times(promotionBundles.getAmount());
    }

    public String getName() {
        return name;
    }
}
