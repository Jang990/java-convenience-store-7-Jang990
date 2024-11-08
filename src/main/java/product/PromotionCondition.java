package product;

public class PromotionCondition {
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
}
