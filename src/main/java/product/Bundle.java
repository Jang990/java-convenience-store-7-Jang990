package product;

public class Bundle extends Quantity {
    private final Quantity remainder;
    private final Quantity unit;

    public Bundle(Quantity base, Quantity unit) {
        super(base.amount / unit.amount);
        remainder = new Quantity(base.amount % unit.amount);
        this.unit = unit;
    }

    public Quantity getRemainder() {
        return remainder;
    }

    public Quantity getUnit() {
        return unit;
    }
}
