package product;

import money.Money;

import java.time.LocalDate;

public class Promotion {
    private final PromotionCondition condition;
    private final PromotionDuration duration;
    private final String name;

    public Promotion(
            PromotionCondition condition,
            PromotionDuration duration,
            String name) {
        this.condition = condition;
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

    public Money apply(Money productPrice, Quantity requested) {
        return productPrice.times(condition.required.amount)
                .times(countAppliedPromotionBundle(requested));
    }

    private int countAppliedPromotionBundle(Quantity saleQuantity) {
        return saleQuantity.divide(condition.getPromotionUnit());
    }
}
