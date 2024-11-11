package store.money;

import store.product.Quantity;

import java.util.Objects;

public class Money {
    private static final int EMPTY_AMOUNT = 0;
    public static final Money EMPTY = new Money(EMPTY_AMOUNT);
    private static final String MINUS_AMOUNT_ERROR_MESSAGE = "돈은 0보다 작을 수 없습니다.";
    private static final String MONEY_FORMAT = "%,3d";

    private final int amount;

    public Money(int amount) {
        if(amount < EMPTY_AMOUNT)
            throw new IllegalArgumentException(MINUS_AMOUNT_ERROR_MESSAGE);

        this.amount = amount;
    }

    public Money plus(Money money) {
        return new Money(amount + money.amount);
    }

    public Money minus(Money money) {
        return new Money(amount - money.amount);
    }

    public Money times(int multiplier) {
        return new Money(amount * multiplier);
    }

    public Money times(Quantity quantity) {
        return new Money(amount * quantity.amount);
    }

    public Money divide(int divisor) {
        return new Money(amount / divisor);
    }

    public boolean isGreaterThan(Money money) {
        return amount > money.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return MONEY_FORMAT.formatted(amount);
    }
}
