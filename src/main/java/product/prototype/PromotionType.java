package product.prototype;

public enum PromotionType {
    ONE_PLUS_ONE(1,1),
    TWO_PLUS_ONE(2,1);

    private final Quantity required;
    private final Quantity free;

    PromotionType(int required, int free) {
        this.required = new Quantity(required);
        this.free = new Quantity(free);
    }

    public Quantity getAppliedUnit() {
        return required.plus(free);
    }
}
