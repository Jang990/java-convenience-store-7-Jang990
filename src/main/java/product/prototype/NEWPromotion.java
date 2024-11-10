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

    public void calculateFree(ProductQuantity requested) throws PromotionException {
        Bundles requestedPromotionBundles = requested.stock()
                .bundleUp(promotionType.getAppliedUnit());

        if(requestedPromotionBundles.hasRemainder())
            throw new PromotionException(
                    "프로모션 적용 수량보다 적게 가져오셨습니다.",
                    requestedPromotionBundles.getShortFall()
            );
    }
}
