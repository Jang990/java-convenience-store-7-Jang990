package product;

public class PromotionCondition {
    private static final String PROMOTION_CONDITION_FORMAT = "%s+%s";
    public final Quantity required;
    public final Quantity free;

    public PromotionCondition(Quantity required, Quantity free) {
        this.required = required;
        this.free = free;
    }

    protected Quantity calculateFreeQuantity(Quantity requested) {
        return free.times(requested.bundleUp(getPromotionUnit()));
    }

    private Quantity getPromotionUnit() {
        return required.plus(free);
    }

    @Override
    public String toString() {
        return PROMOTION_CONDITION_FORMAT.formatted(required, free);
    }
}
