package product.prototype;

public class PromotionBundles extends Quantity {
    private final Quantity remainder;
    private final Quantity unit;
    private final PromotionType appliedPromotion;

    public PromotionBundles(Quantity base, PromotionType appliedPromotion) {
        super(base.amount / appliedPromotion.getAppliedUnit().amount);
        this.unit = appliedPromotion.getAppliedUnit();
        this.remainder = new Quantity(base.amount % unit.amount);
        this.appliedPromotion = appliedPromotion;
    }

    public PromotionBundles(Quantity base, Quantity unit) {
        super(base.amount / unit.amount);
        remainder = new Quantity(base.amount % unit.amount);
        this.unit = unit;
        appliedPromotion = null;
    }

    public boolean hasRemainder() {
        return !Quantity.isEmpty(remainder);
    }

    public Quantity getShortFall() {
        if(hasRemainder())
            return unit.minus(remainder);
        return Quantity.EMPTY;
    }

    public Quantity getRemainder() {
        return remainder;
    }
}
