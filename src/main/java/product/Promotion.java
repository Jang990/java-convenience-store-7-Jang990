package product;

import money.Money;

import java.time.LocalDate;

public class Promotion {
    private final int requiredQuantity;
    private final int freeQuantity;
    private final PromotionDuration duration;
    private final String name;

    public Promotion(PromotionDuration duration, String name) {
        requiredQuantity = 0;
        freeQuantity = 0;
        this.duration = duration;
        this.name = name;
    }

    public Promotion(int requiredQuantity, int freeQuantity, String name, PromotionDuration duration) {
        this.requiredQuantity = requiredQuantity;
        this.freeQuantity = freeQuantity;
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

    public Money apply(Money productPrice, int saleQuantity) {
        return productPrice.times(requiredQuantity)
                .times(countAppliedPromotionBundle(saleQuantity));
    }

    private int countAppliedPromotionBundle(int saleQuantity) {
        return saleQuantity / getPromotionUnit();
    }

    private int getPromotionUnit() {
        return requiredQuantity + freeQuantity;
    }
}
