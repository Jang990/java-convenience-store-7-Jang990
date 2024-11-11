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

        Money discounted = money.divide(10000) .times(3000);
        if (discounted.isGreaterThan(remainingDiscount)) {
            remainingDiscount = Money.EMPTY;
            return discounted;
        }

        //TODO: 멤버쉽 할인 과정 미흡 - 프로모션 이후 금액을 뺴주는 과정 누락
        remainingDiscount = remainingDiscount.minus(discounted);
        return discounted;
    }

    public Money getRemainingDiscount() {
        return remainingDiscount;
    }
}
