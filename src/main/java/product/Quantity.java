package product;

import java.util.Objects;

public class Quantity {
    private static final int EMPTY_AMOUNT = 0;
    private static final String MINUS_QUANTITY_ERROR_MESSAGE = "수량은 마이너스가 될 수 없습니다.";
    public final int amount;

    public Quantity(int amount) {
        if(amount < EMPTY_AMOUNT)
            throw new IllegalArgumentException(MINUS_QUANTITY_ERROR_MESSAGE);
        this.amount = amount;
    }

    public Quantity plus(Quantity quantity) {
        return new Quantity(amount + quantity.amount);
    }

    public int divide(Quantity unit) {
        return amount / unit.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity quantity = (Quantity) o;
        return amount == quantity.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}