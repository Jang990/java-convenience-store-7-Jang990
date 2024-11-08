package product;

import money.Money;

import java.time.LocalDate;

public class Promotion {
    private final Quantity requiredQuantity;
    private final Quantity freeQuantity;
    private final PromotionDuration duration;
    private final String name;

    public Promotion(int requiredQuantity, int freeQuantity, String name, PromotionDuration duration) {
        this.requiredQuantity = new Quantity(requiredQuantity);
        this.freeQuantity = new Quantity(freeQuantity);
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
        return productPrice.times(requiredQuantity.amount)
                .times(countAppliedPromotionBundle(saleQuantity));
    }

    private int countAppliedPromotionBundle(int saleQuantity) {
        return saleQuantity / getPromotionUnit();
    }

    private int getPromotionUnit() {
        return requiredQuantity.amount + freeQuantity.amount;
    }
}
