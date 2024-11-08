package money;

import java.util.Objects;

public class Money {
    private static final int EMPTY_AMOUNT = 0;
    public static final Money EMPTY = new Money(EMPTY_AMOUNT);

    private final int amount;
    private final String MINUS_AMOUNT_ERROR_MESSAGE = "돈은 0보다 작을 수 없습니다.";

    public Money(int amount) {
        if(amount < EMPTY_AMOUNT)
            throw new IllegalArgumentException(MINUS_AMOUNT_ERROR_MESSAGE);

        this.amount = amount;
    }

    public Money plus(Money money) {
        return new Money(amount + money.amount);
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
}
