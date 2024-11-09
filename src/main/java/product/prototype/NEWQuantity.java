package product.prototype;

import java.util.Objects;

public class NEWQuantity {
    private static final int EMPTY_AMOUNT = 0;
    private static final String MINUS_QUANTITY_ERROR_MESSAGE = "수량은 마이너스가 될 수 없습니다.";
    public final int amount;

    public NEWQuantity(int amount) {
        if(amount < EMPTY_AMOUNT)
            throw new IllegalArgumentException(MINUS_QUANTITY_ERROR_MESSAGE);
        this.amount = amount;
    }

    public NEWQuantity plus(NEWQuantity quantity) {
        return new NEWQuantity(amount + quantity.amount);
    }

    public NEWQuantity minus(NEWQuantity quantity) {
        return new NEWQuantity(amount - quantity.amount);
    }

    public NEWQuantity times(NEWQuantity quantity) {
        return new NEWQuantity(amount * quantity.amount);
    }

    public boolean isLessThan(NEWQuantity quantity) {
        return amount < quantity.amount;
    }

    public boolean isGreaterThan(NEWQuantity quantity) {
        return amount > quantity.amount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NEWQuantity quantity = (NEWQuantity) o;
        return amount == quantity.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}