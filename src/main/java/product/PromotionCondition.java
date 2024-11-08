package product;

public class PromotionCondition {
    public final Quantity required;
    public final Quantity free;

    public PromotionCondition(Quantity required, Quantity free) {
        this.required = required;
        this.free = free;
    }

    protected int calculateFreeAmount(Quantity requested) {
        return free.amount * countPromotionBundles(requested);
    }

    private int countPromotionBundles(Quantity requested) {
        return requested.divide(getPromotionUnit());
    }

    private Quantity getPromotionUnit() {
        return required.plus(free);
    }
}
