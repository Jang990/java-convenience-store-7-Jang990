package product.prototype;

import product.PromotionDuration;

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
}