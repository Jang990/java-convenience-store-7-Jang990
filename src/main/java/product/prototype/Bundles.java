package product.prototype;

public class Bundles extends product.Quantity {
    private final Quantity remainder;
    private final Quantity unit;

    public Bundles(Quantity base, Quantity unit) {
        super(base.amount / unit.amount);
        remainder = new Quantity(base.amount % unit.amount);
        this.unit = unit;
    }

    public boolean hasRemainder() {
        return remainder.isGreaterThan(Quantity.EMPTY);
    }

    public Quantity getRemainder() {
        return remainder;
    }
}
