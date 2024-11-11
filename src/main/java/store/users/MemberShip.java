package store.users;

import store.money.Money;

public class MemberShip {
    private static final Money DISCOUNT_LIMIT = new Money(8_000);
    private Money remainingDiscount;

    public MemberShip() {
        this.remainingDiscount = DISCOUNT_LIMIT;
    }

    protected Money discount(Money money) {
        if(remainingDiscount.equals(Money.EMPTY))
            return money;

        Money discounted = money.divide(100) .times(30);
        if (discounted.isGreaterThan(remainingDiscount)) {
            remainingDiscount = Money.EMPTY;
            return money.minus(discounted);
        }

        remainingDiscount = remainingDiscount.minus(discounted);
        return money.minus(discounted);
    }

    public Money getRemainingDiscount() {
        return remainingDiscount;
    }
}
