package store.product;

import java.util.Objects;

public class Quantity {
    private static final int EMPTY_AMOUNT = 0;
    private static final String MINUS_QUANTITY_ERROR_MESSAGE = "수량은 마이너스가 될 수 없습니다.";
    public static final Quantity EMPTY = new Quantity(EMPTY_AMOUNT);
    public static boolean isEmpty(Quantity quantity) {
        return quantity.equals(EMPTY);
    }

    public static Quantity of(int amount) {
        return new Quantity(amount);
    }

    public final int amount;

    public Quantity(int amount) {
        if(amount < EMPTY_AMOUNT)
            throw new IllegalArgumentException(MINUS_QUANTITY_ERROR_MESSAGE);
        this.amount = amount;
    }

    public Quantity plus(Quantity quantity) {
        return new Quantity(amount + quantity.amount);
    }

    public Quantity minus(Quantity quantity) {
        return new Quantity(amount - quantity.amount);
    }

    public Quantity divide(Quantity quantity) {
        return new Quantity(amount / quantity.amount);
    }

    public Quantity times(Quantity quantity) {
        return new Quantity(amount * quantity.amount);
    }

    public boolean isLessThan(Quantity quantity) {
        return amount < quantity.amount;
    }

    public boolean isGreaterThan(Quantity quantity) {
        return amount > quantity.amount;
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

    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}