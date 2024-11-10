package product.prototype;

import product.PromotionDuration;
import product.exception.PromotionException;

import java.time.LocalDate;

public class NEWPromotion {
    public final PromotionType promotionType;
    private final PromotionDuration duration;
    private final String name;

    public NEWPromotion(
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
        if(requested.isEmptyPromotionStock())
            return Quantity.EMPTY;

        Bundles requestedPromotionBundles = requested.stock()
                .bundleUp(promotionType.getAppliedUnit());

        if(requestedPromotionBundles.hasRemainder()
                && requestedPromotionBundles.getShortFall().equals(promotionType.getFree()))
            throw new PromotionException(
                    "무료 제공 수량이 있습니다.",
                    requestedPromotionBundles.getShortFall()
            );

        return null;
    }
}
