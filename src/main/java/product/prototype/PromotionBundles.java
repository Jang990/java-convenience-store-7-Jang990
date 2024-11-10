package product.prototype;

public class PromotionBundles extends Quantity {
    private final Quantity remainder;
    private final PromotionType appliedPromotion;

    public PromotionBundles(Quantity base, PromotionType appliedPromotion) {
        super(base.amount / appliedPromotion.getAppliedUnit().amount);
        this.remainder = new Quantity(base.amount % appliedPromotion.getAppliedUnit().amount);
        this.appliedPromotion = appliedPromotion;
    }

    public boolean hasRemainder() {
        return !Quantity.isEmpty(remainder);
    }

    public Quantity getShortFall() {
        if(hasRemainder())
            return appliedPromotion.getAppliedUnit().minus(remainder);
        return Quantity.EMPTY;
    }

    public Quantity getRemainder() {
        return remainder;
    }
}
